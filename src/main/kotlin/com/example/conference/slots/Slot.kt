package com.example.conference.slots

import com.example.conference.events.Event
import com.example.conference.events.Talk
import java.time.LocalTime

abstract class Slot {
    abstract val startTime: LocalTime
    abstract var restLength: Int

    val addedTime = mutableListOf<LocalTime>()
    var events = mutableListOf<Event>()

    fun arrange(talk: Talk): Boolean? {
        return if (talk.duration.toMinutes() <= restLength) {
            restLength -= talk.duration.toMinutes()
            events.add(talk)
            addedTime.add(addedTime.last().plusMinutes(talk.duration.toMinutes().toLong()))
            true
        } else {
            false
        }
    }

    fun getEvent(Id: Int): Event {
        return events[Id]
    }
}
