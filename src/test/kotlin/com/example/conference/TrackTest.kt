package com.example.conference

import com.example.conference.constant.Constant
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TrackTest {
    @Test
    fun `should be able to get tract by its id each track has unique id`() {
        val track1 = Track(1)
        assertEquals(1, track1.id)
    }
    @Test
    fun `track should have the morning time slot inside`() {
        val track = Track(1)
        assertNotNull(track.morning)
        assertEquals(Constant.MORNING_DURATION, track.morning.length)
        assertEquals(Constant.MORNING_START, track.morning.startTime)
        assertEquals(Constant.MORNING_DURATION, track.morning.unassignedTimeLength)
    }
    @Test
    fun `track should have the afternoon time slot inside`() {
        val track = Track(1)
        assertNotNull(track.afternoon)
        assertEquals(Constant.AFTERNOON_DURATION + Constant.AFTERNOON_ALLOWABLE_DURATION, track.afternoon.length)
        assertEquals(Constant.AFTERNOON_START, track.afternoon.startTime)
        assertEquals(Constant.AFTERNOON_DURATION + Constant.AFTERNOON_ALLOWABLE_DURATION, track.afternoon.unassignedTimeLength)
    }
    @Test
    fun `should be able to transfer to string with expected format`() {
        val track1 = Track(1)
        assertEquals("Track 1:", track1.toString())
    }
}
