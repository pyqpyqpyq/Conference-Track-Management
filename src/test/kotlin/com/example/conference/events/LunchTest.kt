package com.example.conference.events

import com.example.conference.Track
import com.example.conference.constants.Constant
import com.example.conference.events.Lunch.Companion.arrangeLunch
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LunchTest {
    @Test
    fun `should create a lunch time with length of 60 minutes and name is lunch`() {
        val event = Lunch()
        Assertions.assertEquals("Lunch", event.name)
        Assertions.assertEquals(60, event.duration.toMinutes())
    }
    @Test
    fun `should be able to arrange lunch for a morning`() {
        val tracks = mutableListOf(Track(1))
        arrangeLunch(tracks)
        Assertions.assertEquals("Lunch", tracks[0].morning.events[0].name)
        Assertions.assertEquals(Constant.LUNCH_TIME, tracks[0].morning.addedTime.last())
    }
}
