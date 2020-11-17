package com.example.project3.model

import com.example.project3.model.events.NetworkEvent
import com.example.project3.model.events.Talk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EventTest {
    @Test
    fun `should create a talk by giving name and the time duration of the event, and can get the name and time duration`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, duration)
        Assertions.assertEquals(event.name, "Writing Fast Tests Against Enterprise Rails")
        Assertions.assertEquals(event.duration, "60min")
    }
    @Test
    fun `should create a networkEvent by giving  the time duration of the event, and can get the name and time duration`() {
        val duration = 60
        val event = NetworkEvent(duration)
        Assertions.assertEquals(event.name, "Networking Event")
        Assertions.assertEquals(event.duration, "60min")
    }
}
