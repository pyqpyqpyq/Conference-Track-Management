package com.example.conference

import com.example.conference.constants.Constant
import com.example.conference.durations.Duration
import com.example.conference.durations.Lightning
import com.example.conference.durations.Minutes
import com.example.conference.events.Lunch
import com.example.conference.events.NetworkEvent
import com.example.conference.events.Talk
import com.example.conference.slots.Slot
import java.time.LocalTime

class ConferenceManager {

    fun transferStringToDuration(input: String): Duration {
        return if (input == "lightning") {
            Lightning()
        } else {
            val num = input.replace(Regex("[^0-9]"), "").toInt()
            Minutes(num)
        }
    }
    fun transferStringToTalk(string: String): Talk {
        val wordList = string.split(' ')
        val name = wordList.subList(0, wordList.size - Constant.LAST_PART_REPRESENT_DURATION).joinToString(" ")
        val duration = transferStringToDuration(wordList[wordList.size - Constant.HUMAN_COMPUTER_DISTANCE])
        return Talk(name, duration)
    }
    fun transferStringListToTalkList(input: MutableList<String>): MutableList<Talk> {
        val talkList = mutableListOf<Talk>()
        input.stream().forEach { talkList.add(transferStringToTalk(it)) }
        return talkList
    }
    fun addTrack(tracks: MutableList<Track>) {
        tracks.add(Track(tracks.size + Constant.HUMAN_COMPUTER_DISTANCE))
    }
    fun getTrack(tracks: MutableList<Track>, id: Int): Track {
        return tracks[id - Constant.HUMAN_COMPUTER_DISTANCE]
    }
    fun rankTalks(inputList: MutableList<Talk>): MutableList<Talk> {
        inputList.sortByDescending { it.duration.toMinutes() }
        return inputList
    }
    fun rankSlots(inputList: MutableList<Slot>): MutableList<Slot> {
        inputList.sortByDescending { it.restLength }
        return inputList
    }
    fun getAllSlots(tracks: MutableList<Track>): MutableList<Slot> {
        val availableSlots = mutableListOf<Slot>()
        for (anyTrack in tracks) {
            availableSlots.add(anyTrack.morning)
            availableSlots.add(anyTrack.afternoon)
        }
        return availableSlots
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
    fun arrangeLunch(tracks: MutableList<Track>): MutableList<Track> {
        tracks.forEach { track ->
            track.morning.events.add(Lunch())
            track.morning.addedTime.removeLast()
            track.morning.addedTime.add(LocalTime.of(12, 0))
        }
        return tracks
    }
    fun arrangeNetworkEvent(tracks: MutableList<Track>): MutableList<Track> {
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
        return tracks
    }
}
