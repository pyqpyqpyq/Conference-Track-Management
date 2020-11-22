package com.example.conference.events

import com.example.conference.Track
import com.example.conference.constants.Constant
import com.example.conference.events.NetworkEvent.Companion.arrangeNetworkEvent
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NetworkEventTest {
    @Test
    fun `should create a networkEvent by giving  the time duration of the event, and can get the name and time duration`() {
        val event = NetworkEvent()
        Assertions.assertEquals("Networking Event", event.name)
    }
    @Test
    fun `should be able to arrange networkEvent for a Afternoon`() {
        val tracks = mutableListOf(Track(1))
        arrangeNetworkEvent(tracks)
        Assertions.assertEquals("Networking Event", tracks[0].afternoon.events[0].name)
        Assertions.assertEquals(Constant.NETWORK_EVENT_TIME, tracks[0].afternoon.addedTime.last())
    }
}
