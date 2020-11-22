package com.example.conference.slots

import com.example.conference.events.Event
import com.example.conference.events.Talk
import java.time.LocalTime

abstract class Slot(val startTime: LocalTime, var unassignedTimeLength: Int) {

    val addedTime = mutableListOf<LocalTime>()
    var arrangedEvents = mutableListOf<Event>()

    init {
        addedTime.add(startTime)
    }

    fun put(talk: Talk): Boolean? {
        return if (talk.duration.toMinutes() <= unassignedTimeLength) {
            this.unassignedTimeLength -= talk.duration.toMinutes()
            arrangedEvents.add(talk)
            addedTime.add(addedTime.last().plusMinutes(talk.duration.toMinutes().toLong()))
            true
        } else {
            false
        }
    }
}
