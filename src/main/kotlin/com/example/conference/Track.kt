package com.example.conference

import com.example.conference.slots.Afternoon
import com.example.conference.slots.Morning

data class Track(var id: Int) {
    var morning = Morning()
    var afternoon = Afternoon()
}
