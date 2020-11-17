package com.example.project3.model.events

class NetworkEvent(val duration: Int) : Event() {
    val name = "Networking Event"
    override fun name(): String {
        return name
    }

    override fun duration(): Int {
        return duration
    }
}
