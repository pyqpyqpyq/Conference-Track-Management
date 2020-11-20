package com.example.conference

import com.example.conference.Track.Companion.addTrack
import com.example.conference.constants.Constant
import com.example.conference.events.Lunch
import com.example.conference.events.NetworkEvent
import com.example.conference.events.Talk
import com.example.conference.events.Talk.Companion.rankTalks
import com.example.conference.events.Talk.Companion.transferStringToTalk
import com.example.conference.slots.Slot
import com.example.conference.slots.Slot.Companion.rankSlots
import java.io.File
import java.io.InputStream
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class ConferenceManager {

    fun arrangeConference() {
        val inputTalks = transferStringListToTalkList(read())
        output(arrangeConferenceWithNTracks(inputTalks, Constant.ONE_MORE_TRACK))
    }

    fun read(): MutableList<String> {
        val inputStream: InputStream = File(Constant.INPUT_FILE_PATH).inputStream()
        val talkListString = mutableListOf<String>()
        inputStream.bufferedReader().forEachLine { talkListString.add(it) }
        return talkListString
    }

    fun output(tracks: MutableList<Track>) {
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
    fun transferStringListToTalkList(input: MutableList<String>): MutableList<Talk> {
        val talkList = mutableListOf<Talk>()
        input.stream().forEach { talkList.add(transferStringToTalk(it)) }
        return talkList
    }
    fun getAllSlotsByOrder(tracks: MutableList<Track>): MutableList<Slot> {
        val availableSlots = mutableListOf<Slot>()
        for (anyTrack in tracks) {
            availableSlots.add(anyTrack.morning)
            availableSlots.add(anyTrack.afternoon)
        }
        return rankSlots(availableSlots)
    }
    fun arrangeOneTalk(talks: MutableList<Talk>, slots: MutableList<Slot>): Boolean {
        rankTalks(talks)
        return if (slots[0].arrange(talks[0])!!) {
            talks.removeAt(0)
            true
        } else {
            false
        }
    }
    private fun arrangeLunch(tracks: MutableList<Track>): MutableList<Track> {
        tracks.forEach { track ->
            track.morning.events.add(Lunch())
            track.morning.addedTime.removeLast()
            track.morning.addedTime.add(LocalTime.of(12, 0))
        }
        return tracks
    }
    private fun arrangeNetworkEvent(tracks: MutableList<Track>): MutableList<Track> {
        tracks.forEach { track ->
            track.afternoon.events.add(NetworkEvent())
            track.afternoon.addedTime.removeLast()
            track.afternoon.addedTime.add(LocalTime.of(17, 0))
        }
        return tracks
    }
    fun arrangeConferenceWithNTracks(talks: MutableList<Talk>, n: Int): MutableList<Track> {
        val tracks = mutableListOf<Track>()
        repeat(n) { addTrack(tracks) }
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
}
