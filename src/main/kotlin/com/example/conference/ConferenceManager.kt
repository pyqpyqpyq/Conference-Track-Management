package com.example.conference

import com.example.conference.constants.Constant.Companion.File_NOT_FOUND
import com.example.conference.constants.Constant.Companion.INVALID_INPUT_CONTAIN_DIGIT
import com.example.conference.constants.Constant.Companion.INVALID_INPUT_TIME_FORMAT
import com.example.conference.constants.Constant.Companion.ONE_MORE_TRACK
import com.example.conference.events.Lunch.Companion.arrangeLunch
import com.example.conference.events.NetworkEvent.Companion.arrangeNetworkEvent
import com.example.conference.events.Talk
import com.example.conference.exceptions.NameContainsDigitException
import com.example.conference.exceptions.WithoutInvalidTimeException
import com.example.conference.slots.Slot
import com.example.conference.utils.TransferUtil.transferStringListToTalkList
import com.example.conference.utils.ValidateUtil.validate
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
        }
    }
    private fun readInput(inputSRC: String): MutableList<String> {
        val talkListString = mutableListOf<String>()
        File(inputSRC).useLines { lines -> talkListString.addAll(lines) }
        return talkListString
    }

    fun arrangeEvents(talks: MutableList<Talk>, n: Int): MutableList<Track> {
        val tracks = mutableListOf<Track>()
        repeat(n) { addOneTrack(tracks) }
        val copyTalks = mutableListOf<Talk>().apply { addAll(talks) }
        while (copyTalks.isNotEmpty()) {
            if (!arrangeOneTalk(copyTalks, getAllSlotsByOrder(tracks))) {
                return arrangeEvents(talks, n + ONE_MORE_TRACK)
            }
        }
        arrangeLunch(tracks)
        arrangeNetworkEvent(tracks)
        return tracks
    }

    fun printResult(tracks: MutableList<Track>) {
        tracks.forEach { track ->
            println(track.toString())
            for (index in 0 until track.morning.arrangedEvents.size) {
                print(track.morning.addedTime[index].format(DateTimeFormatter.ofPattern("hh:mma ", Locale.ENGLISH)))
                println(track.morning.arrangedEvents[index].toString())
            }
            for (index in 0 until track.afternoon.arrangedEvents.size) {
                print(track.afternoon.addedTime[index].format(DateTimeFormatter.ofPattern("hh:mma ", Locale.ENGLISH)))
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
        return if (slots[0].put(talks[0])!!) {
            talks.removeAt(0)
            true
        } else {
            false
        }
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
