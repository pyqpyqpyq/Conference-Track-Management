package com.example.project3.model

import com.example.project3.model.events.Event
import com.example.project3.model.events.Talk
import com.example.project3.model.slots.Slot
import com.example.project3.utils.Constant
import com.example.project3.utils.Input
import com.sun.javafx.embed.AbstractEvents

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
    fun arrangeOneEvent(events: MutableList<Event>, slots:MutableList<Slot>){
        slots[0].arrange(events[0])
        events.removeAt(0)
    }



}
