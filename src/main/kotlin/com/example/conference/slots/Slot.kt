package com.example.conference.slots

import com.example.conference.events.Event
import com.example.conference.events.Talk
import java.time.LocalTime

abstract class Slot {
//    abstract val startTime: LocalTime
    private var addedTime = mutableListOf<LocalTime>()
    private var events = mutableListOf<Event>()
    abstract var restLength: Int
    fun arrange(talk: Talk) {
        restLength -= talk.duration.toMinutes()
        events.add(talk)
    }
    fun getEvent(Id: Int): Event {
        return events[Id]
    }
}
