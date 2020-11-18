package com.example.project3.model.events

import com.example.project3.model.Duration

class NetworkEvent(val duration: Duration) : Event() {
    val name = "Networking Event"
    override fun name(): String {
        return name
    }

    override fun duration(): Int {
        return duration.toInt()
    }
}
