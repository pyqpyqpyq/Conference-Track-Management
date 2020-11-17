package com.example.project3.model.slots

import com.example.project3.model.events.Event

abstract class Slot {
    private var events = mutableListOf<Event>()
    abstract var restLength: Int
    fun arrange(event: Event) {
        restLength -= event.duration()
        events.add(event)
    }
}
