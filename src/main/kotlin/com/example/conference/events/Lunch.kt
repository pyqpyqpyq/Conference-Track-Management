package com.example.conference.events

import com.example.conference.durations.Minutes

class Lunch : Event() {
    val name = "Lunch"
    val duration = Minutes(60)
    override fun toString(): String {
        return "$name $duration"
    }
}
