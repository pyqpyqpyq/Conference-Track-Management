package com.example.conference.slot

import com.example.conference.event.Event
import com.example.conference.event.Talk
import java.time.LocalTime

abstract class Slot(val startTime: LocalTime, var unassignedTimeLength: Int) {

    val addedTime = mutableListOf<LocalTime>()
    var arrangedEvents = mutableListOf<Event>()

    init {
        addedTime.add(startTime)
    }

    fun put(talk: Talk): Boolean {
        if (talk.duration.toMinutes() > unassignedTimeLength) {
            return false
        }
        this.unassignedTimeLength -= talk.duration.toMinutes()
        arrangedEvents.add(talk)
        addedTime.add(addedTime.last().plusMinutes(talk.duration.toMinutes().toLong()))
        return true
    }
}
