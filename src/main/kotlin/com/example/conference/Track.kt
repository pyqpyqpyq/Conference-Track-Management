package com.example.conference

import com.example.conference.slots.Afternoon
import com.example.conference.slots.Morning

class Track(val id: Int) {
    val morning = Morning()
    val afternoon = Afternoon()
    override fun equals(other: Any?): Boolean {
        return when (other) {
            !is Track -> false
            else ->
                id == other.id
        }
    }
}
