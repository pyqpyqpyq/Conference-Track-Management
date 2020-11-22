package com.example.conference

import com.example.conference.Track.Companion.addOneMoreTrack
import com.example.conference.Track.Companion.getAllSlotsByOrder
import com.example.conference.constants.Constant
import com.example.conference.events.Lunch.Companion.arrangeLunch
import com.example.conference.events.NetworkEvent.Companion.arrangeNetworkEvent
import com.example.conference.events.Talk
import com.example.conference.slots.Slot
import com.example.conference.utils.TransferUtil.Companion.transferStringListToTalkList
import com.example.conference.utils.ValidateUtil.Companion.validate
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.format.DateTimeFormatter
import java.util.Locale

class ConferenceManager {

    fun manageConference() {
        val inputString = readInput()
        if (validate(inputString)) {
            val inputTalks = transferStringListToTalkList(inputString)
            printResult(arrangeTalks(inputTalks, Constant.ONE_MORE_TRACK))
        }
    }
    fun readInput(): MutableList<String> {
        val bufferedReader = BufferedReader(FileReader(File(Constant.INPUT_FILE_PATH)))
        val talkListString = mutableListOf<String>()
        bufferedReader.forEachLine { talkListString.add(it) }
        return talkListString
    }

    fun arrangeTalks(talks: MutableList<Talk>, n: Int): MutableList<Track> {
        val tracks = mutableListOf<Track>()
        repeat(n) { addOneMoreTrack(tracks) }
        val copyTalks = mutableListOf<Talk>().apply { addAll(talks) }
        while (copyTalks.isNotEmpty()) {
            if (!arrangeOneTalk(copyTalks, getAllSlotsByOrder(tracks))) {
                return arrangeTalks(talks, n + Constant.ONE_MORE_TRACK)
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
        return if (slots[0].arrange(talks[0])!!) {
            talks.removeAt(0)
            true
        } else {
            false
        }
    }
}
