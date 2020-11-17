package com.example.project3.model.conferences

import com.example.project3.model.events.Event

class Slot {
    var events = mutableListOf<Event>()
    fun arrange(event: Event) {
        events.add(event)
    }
}
