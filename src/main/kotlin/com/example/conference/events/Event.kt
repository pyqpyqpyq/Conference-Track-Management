package com.example.conference.events

abstract class Event(val name: String) {
    override fun toString(): String {
        return name
    }
}
