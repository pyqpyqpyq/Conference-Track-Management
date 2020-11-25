package com.example.conference

import com.example.conference.constant.Constant.File_NOT_FOUND
import com.example.conference.constant.Constant.INVALID_INPUT_CONTAIN_DIGIT
import com.example.conference.constant.Constant.INVALID_INPUT_TIME_FORMAT
import com.example.conference.constant.Constant.ONE_MORE_TRACK
import com.example.conference.constant.Constant.UNEXPECTED_EXCEPTION
import com.example.conference.event.Lunch.Companion.arrangeLunch
import com.example.conference.event.NetworkEvent.Companion.arrangeNetworkEvent
import com.example.conference.event.Talk
import com.example.conference.exception.NameContainsDigitException
import com.example.conference.exception.WithoutInvalidTimeException
import com.example.conference.slot.Slot
import com.example.conference.util.TransferUtil.transferStringListToTalkList
import com.example.conference.util.ValidateUtil.validate
import java.io.File
import java.io.FileNotFoundException
import java.time.format.DateTimeFormatter
import java.util.Locale

class ConferenceManager {
    fun manageConference(inputSRC: String) {
        try {
            val inputString = readInput(inputSRC)
            validate(inputString)
            val inputTalks = transferStringListToTalkList(inputString)
            printResult(arrangeEvents(inputTalks, ONE_MORE_TRACK))
        } catch (exception: FileNotFoundException) {
            println(File_NOT_FOUND)
        } catch (exception: WithoutInvalidTimeException) {
            println(INVALID_INPUT_TIME_FORMAT)
        } catch (exception: NameContainsDigitException) {
            println(INVALID_INPUT_CONTAIN_DIGIT)
        } catch (exception: Throwable) {
            println(UNEXPECTED_EXCEPTION)
        }
    }

    fun readInput(inputSRC: String): MutableList<String> {
        val talkListString = mutableListOf<String>()
        File(inputSRC).useLines { lines -> talkListString.addAll(lines) }
        return talkListString
    }

    fun arrangeEvents(talks: MutableList<Talk>, n: Int): MutableList<Track> {
        val tracks = mutableListOf<Track>()
        repeat(n) { addOneTrack(tracks) }
        val copyTalks = mutableListOf<Talk>().apply { addAll(talks) }
        while (copyTalks.isNotEmpty()) {
            // if failed, call itself by adding one more track
            if (!arrangeOneTalk(copyTalks, getAllSlotsByOrder(tracks))) {
                return arrangeEvents(talks, n + ONE_MORE_TRACK)
            }
        }
        // if success, arrange lunch and networkEvent and return
        arrangeLunch(tracks)
        arrangeNetworkEvent(tracks)
        return tracks
    }

    fun printResult(tracks: MutableList<Track>) {
        tracks.forEach { track ->
            println(track.toString())
            // for morning
            for (index in 0 until track.morning.arrangedEvents.size) {
                // print track title
                print(track.morning.addedTime[index].format(DateTimeFormatter.ofPattern("hh:mma ", Locale.ENGLISH)))
                // print arrangements
                println(track.morning.arrangedEvents[index].toString())
            }
            // for afternoon
            for (index in 0 until track.afternoon.arrangedEvents.size) {
                // print track title
                print(track.afternoon.addedTime[index].format(DateTimeFormatter.ofPattern("hh:mma ", Locale.ENGLISH)))
                // print arrangements
                println(track.afternoon.arrangedEvents[index].toString())
            }
        }
    }

    fun rankSlots(inputList: MutableList<Slot>): MutableList<Slot> {
        inputList.sortByDescending { it.unassignedTimeLength }
        return inputList
    }

    fun arrangeOneTalk(talks: MutableList<Talk>, slots: MutableList<Slot>): Boolean {
        Talk.rankTalks(talks)
        if (slots[0].put(talks[0])) {
            talks.removeAt(0)
            return true
        }
        return false
    }

    fun addOneTrack(tracks: MutableList<Track>) {
        tracks.add(Track(tracks.size + ONE_MORE_TRACK))
    }

    fun getAllSlotsByOrder(tracks: MutableList<Track>): MutableList<Slot> {
        val conferenceManager = ConferenceManager()
        val availableSlots = mutableListOf<Slot>()
        for (anyTrack in tracks) {
            availableSlots.add(anyTrack.morning)
            availableSlots.add(anyTrack.afternoon)
        }
        return conferenceManager.rankSlots(availableSlots)
    }
}
