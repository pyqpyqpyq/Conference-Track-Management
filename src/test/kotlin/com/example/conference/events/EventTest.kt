package com.example.conference.events

import com.example.conference.Duration
import com.example.conference.TimeUnit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EventTest {
    @Test
    fun `should create a talk by giving name and the time duration of the event, and can get the name and time duration`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Duration(duration, TimeUnit.MINUTES))
        Assertions.assertEquals(event.name, "Writing Fast Tests Against Enterprise Rails")
        Assertions.assertEquals(event.duration.toInt(), 60)
    }
    @Test
    fun `should create a networkEvent by giving  the time duration of the event, and can get the name and time duration`() {
        val duration = 60
        val event = NetworkEvent(Duration(duration, TimeUnit.MINUTES))
        Assertions.assertEquals(event.name, "Networking Event")
        Assertions.assertEquals(event.duration.toInt(), 60)
    }
    @Test
    fun `should create a lunch time with length of 60 minutes and name is lunch`() {
        val event = Lunch()
        Assertions.assertEquals(event.name, "Lunch")
        Assertions.assertEquals(event.duration, 60)
    }
}
