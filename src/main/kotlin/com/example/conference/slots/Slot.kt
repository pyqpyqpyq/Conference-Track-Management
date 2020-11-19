package com.example.conference.slots

import com.example.conference.events.Event
import com.example.conference.events.Talk
import java.time.LocalTime

abstract class Slot {
    abstract val startTime: LocalTime
    val addedTime = mutableListOf<LocalTime>()
    private var events = mutableListOf<Event>()
    abstract var restLength: Int
    fun arrange(talk: Talk) {
        restLength -= talk.duration.toMinutes()
        events.add(talk)
        addedTime.add(addedTime.last().plusMinutes(talk.duration.toMinutes().toLong()))
    }
    fun getEvent(Id: Int): Event {
        return events[Id]
    }
}