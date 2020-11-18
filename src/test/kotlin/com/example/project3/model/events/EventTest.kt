package com.example.project3.model.events

import com.example.project3.model.Duration
import com.example.project3.model.TimeUnit
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
}
