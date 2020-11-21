package com.example.conference

import com.example.conference.Track.Companion.addOneMoreTrack
import com.example.conference.Track.Companion.getAllSlotsByOrder
import com.example.conference.constants.Constant
import com.example.conference.events.Lunch.Companion.arrangeLunch
import com.example.conference.events.NetworkEvent.Companion.arrangeNetworkEvent
import com.example.conference.events.Talk
import com.example.conference.events.Talk.Companion.transferStringListToTalkList
import com.example.conference.events.Talk.Companion.validateInput
import com.example.conference.slots.Slot.Companion.arrangeOneTalk
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.format.DateTimeFormatter
import java.util.Locale

class ConferenceManager {

    fun arrangeConference() {
        val inputString = readFromFile()
        if (validate(inputString)) {
            val inputTalks = transferStringListToTalkList(inputString)
            outputToConsole(arrangeConferenceWithNTracks(inputTalks, Constant.ONE_MORE_TRACK))
        }
    }
    fun readFromFile(): MutableList<String> {
        val bufferedReader = BufferedReader(FileReader(File(Constant.INPUT_FILE_PATH)))
        val talkListString = mutableListOf<String>()
        bufferedReader.forEachLine { talkListString.add(it) }
        return talkListString
    }
    fun validate(inputString: MutableList<String>): Boolean {
        return if (!validateInput(inputString)) {
            print("Invalid input please check again")
            false
        } else {
            true
        }
    }
    fun arrangeConferenceWithNTracks(talks: MutableList<Talk>, n: Int): MutableList<Track> {
        val tracks = mutableListOf<Track>()
        repeat(n) { addOneMoreTrack(tracks) }
        val copyTalks = mutableListOf<Talk>().apply { addAll(talks) }
        while (copyTalks.isNotEmpty()) {
            if (!arrangeOneTalk(copyTalks, getAllSlotsByOrder(tracks))) {
                return arrangeConferenceWithNTracks(talks, n + Constant.ONE_MORE_TRACK)
            }
        }
        arrangeLunch(tracks)
        arrangeNetworkEvent(tracks)
        return tracks
    }
    fun outputToConsole(tracks: MutableList<Track>) {
        tracks.forEach { track ->
            println(track.toString())
            for (index in 0 until track.morning.events.size) {
                print(track.morning.addedTime[index].format(DateTimeFormatter.ofPattern("hh:mma ", Locale.ENGLISH)))
                println(track.morning.events[index].toString())
            }
            for (index in 0 until track.afternoon.events.size) {
                print(track.afternoon.addedTime[index].format(DateTimeFormatter.ofPattern("hh:mma ", Locale.ENGLISH)))
                println(track.afternoon.events[index].toString())
            }
        }
    }
}
