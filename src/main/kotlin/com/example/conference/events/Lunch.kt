package com.example.conference.events

class Lunch: Event() {
    val name = "Lunch"
    val duration =60
    override fun name(): String {
        return name
    }

    override fun duration(): Int {
        return duration
    }
}
