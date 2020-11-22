package com.example.conference

import com.example.conference.slot.Afternoon
import com.example.conference.slot.Morning

class Track(val id: Int) {
    val morning = Morning()
    val afternoon = Afternoon()
    override fun toString(): String {
        return "Track $id:"
    }
}
