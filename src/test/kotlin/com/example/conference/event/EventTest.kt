package com.example.conference.event

import com.example.conference.duration.Minutes
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EventTest {
    @Test
    fun `should be able to toString to display for the talk`() {
        val name1 = "Writing Fast Tests Against Enterprise Rails"
        val duration1 = Minutes(60)
        val event1 = Talk(name1, duration1)
        val event2 = Lunch()
        val event3 = NetworkEvent()
        assertEquals("Writing Fast Tests Against Enterprise Rails 60min", event1.toString())
        assertEquals("Lunch", event2.toString())
        assertEquals("Networking Event", event3.toString())
    }
}
