package com.example.conference

import com.example.conference.constants.Constant
import com.example.conference.events.Talk
import com.example.conference.slots.Slot

class ConferenceManager {
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
    fun arrangeConferenceWithNTracks(talks: MutableList<Talk>, n: Int): Boolean {
        val tracks = mutableListOf<Track>()
        repeat(n) { addTrack(tracks) }
        val copyTalks = mutableListOf<Talk>().apply { addAll(talks) }
        while (copyTalks.isNotEmpty()) {
            if (!arrangeOneTalk(copyTalks, getAllSlotsByOrder(tracks))) {
                return arrangeConferenceWithNTracks(talks, n + Constant.ONE_MORE_TRACK)
            }
        }
        return true
    }
}
