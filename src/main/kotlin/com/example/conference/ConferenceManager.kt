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
import java.time.LocalTime

class ConferenceManager {

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
        arrangeLunch(tracks)
        arrangeNetworkEvent(tracks)
        return tracks
    }
}
