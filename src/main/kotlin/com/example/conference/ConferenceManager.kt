package com.example.conference

import com.example.conference.constants.Constant
import com.example.conference.events.Talk
import com.example.conference.slots.Slot

class ConferenceManager {
    private var tracks = mutableListOf<Track>()
    init {
        addTrack()
    }
    fun addTrack() {
        tracks.add(Track(tracks.size + Constant.HUMAN_COMPUTER_DISTANCE))
    }
    fun getTrack(id: Int): Track {
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
    fun getAllSlots(): MutableList<Slot> {
        val availableSlots = mutableListOf<Slot>()
        for (anyTrack in tracks) {
            availableSlots.add(anyTrack.morning)
            availableSlots.add(anyTrack.afternoon)
        }
        return availableSlots
    }
    fun getAllSlotsByOrder(): MutableList<Slot> {
        val availableSlots = mutableListOf<Slot>()
        for (anyTrack in tracks) {
            availableSlots.add(anyTrack.morning)
            availableSlots.add(anyTrack.afternoon)
        }
        return rankSlots(availableSlots)
    }
    fun arrangeOneTalk(events: MutableList<Talk>, slots: MutableList<Slot>) {
        slots[0].arrange(events[0])
        events.removeAt(0)
    }
    fun arrangeConference(talks: MutableList<Talk>) {
        rankTalks(talks)
        while (talks.isNotEmpty()) {
            arrangeOneTalk(talks, getAllSlotsByOrder())
        }
    }
}
