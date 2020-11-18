package com.example.project3.model

import com.example.project3.model.events.Talk
import com.example.project3.model.slots.Slot
import com.example.project3.utils.Constant

class Conference {
    private var tracks = mutableListOf<Track>()

    fun addTrack() {
        tracks.add(Track(tracks.size + Constant.HUMAN_COMPUTER_DISTANCE))
    }
    fun getTrack(id: Int): Track {
        return tracks[id - Constant.HUMAN_COMPUTER_DISTANCE]
    }
    fun rankTalks(inputList: MutableList<Talk>): MutableList<Talk> {
        inputList.sortByDescending { it.duration() }
        return inputList
    }
    fun rankSlots(inputList: MutableList<Slot>): MutableList<Slot> {
        inputList.sortByDescending { it.restLength }
        return inputList
    }
    fun getAllSlots(): MutableList<Slot> {
        var availableSlots = mutableListOf<Slot>()
        for (anyTrack in tracks) {
            availableSlots.add(anyTrack.morning)
            availableSlots.add(anyTrack.afternoon)
        }
        return availableSlots
    }
}
