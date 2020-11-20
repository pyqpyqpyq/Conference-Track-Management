package com.example.conference.events

import com.example.conference.durations.Duration

class Talk(val name: String, val duration: Duration) : Event() {
     override fun toString(): String {
        return "$name $duration"
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            !is Talk -> false
            else -> name == other.name && duration == other.duration
        }
}}
