package com.example.conference.event

abstract class Event(val name: String) {
    override fun toString(): String {
        return name
    }
}
