package com.example.project3.model

class NetworkEvent(val duration: String) : Event() {
    var name: String = "Networking Event"
    override fun name(): String {
        return name
    }

    override fun duration(): String {
        return duration
    }
}
