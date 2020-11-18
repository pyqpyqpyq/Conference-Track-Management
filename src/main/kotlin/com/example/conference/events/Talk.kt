package com.example.conference.events

import com.example.conference.Duration

data class Talk(var name: String, var duration: Duration) : Event() {
    override fun name(): String {
        return name
    }
    override fun duration(): Int {
        return duration.toInt()
    }
}
