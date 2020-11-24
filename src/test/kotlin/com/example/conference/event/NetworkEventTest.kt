package com.example.conference.event

import com.example.conference.Track
import com.example.conference.constant.Constant
import com.example.conference.event.NetworkEvent.Companion.arrangeNetworkEvent
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NetworkEventTest {
    @Test
    fun `should create a networkEvent by giving  the time duration of the event, and can get the name and time duration`() {
        val event = NetworkEvent()
        assertEquals("Networking Event", event.name)
    }

    @Test
    fun `should be able to toString to display for the talk`() {
        val event = NetworkEvent()
        Assertions.assertEquals("Networking Event", event.toString())
    }

    @Test
    fun `should be able to arrange networkEvent for a Afternoon`() {
        val tracks = mutableListOf(Track(1))
        arrangeNetworkEvent(tracks)
        assertEquals("Networking Event", tracks[0].afternoon.arrangedEvents[0].name)
        assertEquals(Constant.NETWORK_EVENT_TIME, tracks[0].afternoon.addedTime.last())
    }
}
