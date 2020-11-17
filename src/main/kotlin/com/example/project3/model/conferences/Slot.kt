package com.example.project3.model.conferences

import com.example.project3.model.events.Event

abstract class Slot {
    var events = mutableListOf<Event>()
    abstract var restTime:Int
     fun arrange(event: Event) {
         restTime-=event.duration()
         events.add(event)
     }
}
