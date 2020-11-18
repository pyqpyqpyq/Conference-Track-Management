package com.example.conference.events

import com.example.conference.Duration

class NetworkEvent(val duration: Duration) : Event() {
    val name = "Networking Event"
    override fun name(): String {
        return name
    }

    override fun duration(): Int {
        return duration.toInt()
    }
}
