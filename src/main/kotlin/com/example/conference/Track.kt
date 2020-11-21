package com.example.conference

import com.example.conference.constants.Constant
import com.example.conference.slots.Afternoon
import com.example.conference.slots.Morning
import com.example.conference.slots.Slot

class Track(val id: Int) {
    val morning = Morning()
    val afternoon = Afternoon()
    override fun toString(): String {
        return "Track $id:"
    }
    companion object {
        fun addTrack(tracks: MutableList<Track>) {
            tracks.add(Track(tracks.size + Constant.HUMAN_COMPUTER_DISTANCE))
        }
        fun getTrack(tracks: MutableList<Track>, id: Int): Track {
            return tracks[id - Constant.HUMAN_COMPUTER_DISTANCE]
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
            return Slot.rankSlots(availableSlots)
        }
    }
}
