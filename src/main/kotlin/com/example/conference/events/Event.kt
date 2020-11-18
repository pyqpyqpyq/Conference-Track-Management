package com.example.conference.events

abstract class Event {
    abstract fun name(): String
    abstract fun duration(): Int
}
