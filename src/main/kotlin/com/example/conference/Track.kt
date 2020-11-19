package com.example.conference

import com.example.conference.slots.Afternoon
import com.example.conference.slots.Morning

class Track(var id: Int) {
    var morning = Morning()
    var afternoon = Afternoon()
    override fun equals(other: Any?): Boolean {
        return when (other) {
            !is Track -> false
            else ->
                this === other ||
                    id == other.id
        }
    }
}
