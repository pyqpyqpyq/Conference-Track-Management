package com.example.conference.slots

import com.example.conference.events.Event
import com.example.conference.events.Talk

abstract class Slot {
    private var events = mutableListOf<Event>()
    abstract var restLength: Int
    fun arrange(talk: Talk) {
        restLength -= talk.duration.toInt()
        events.add(talk)
    }
    fun getEvent(Id: Int): Event {
        return events[Id]
    }
}
