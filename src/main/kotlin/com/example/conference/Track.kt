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
        fun addOneMoreTrack(tracks: MutableList<Track>) {
            tracks.add(Track(tracks.size + Constant.ONE_MORE_TRACK))
        }
        fun getAllSlotsByOrder(tracks: MutableList<Track>): MutableList<Slot> {
            val conferenceManager = ConferenceManager()
            val availableSlots = mutableListOf<Slot>()
            for (anyTrack in tracks) {
                availableSlots.add(anyTrack.morning)
                availableSlots.add(anyTrack.afternoon)
            }
            return conferenceManager.rankSlots(availableSlots)
        }
    }
}
