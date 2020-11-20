package com.example.conference.events

class NetworkEvent : Event() {
    val name = "Networking Event"
    override fun toString(): String {
        return "$name"
    }
}
