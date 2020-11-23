package com.example.conference.event

import com.example.conference.Track
import com.example.conference.constant.Constant
import com.example.conference.event.Lunch.Companion.arrangeLunch
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LunchTest {
    @Test
    fun `should create a lunch time with length of 60 minutes and name is lunch`() {
        val event = Lunch()
        assertEquals("Lunch", event.name)
        assertEquals(60, event.duration.toMinutes())
    }
    @Test
    fun `should be able to arrange lunch for a morning`() {
        val tracks = mutableListOf(Track(1))
        arrangeLunch(tracks)
        assertEquals("Lunch", tracks[0].morning.arrangedEvents[0].name)
        assertEquals(Constant.LUNCH_TIME, tracks[0].morning.addedTime.last())
    }
}
