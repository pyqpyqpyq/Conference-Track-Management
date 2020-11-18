package com.example.conference.slots

import com.example.conference.events.Event

abstract class Slot {
    private var events = mutableListOf<Event>()
    abstract var restLength: Int
    fun arrange(event: Event) {
        restLength -= event.duration()
        events.add(event)
    }
    fun getEvent(Id: Int): Event {
        return events[Id]
    }
}
