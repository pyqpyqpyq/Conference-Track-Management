package com.example.conference

import com.example.conference.constants.Constant
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TrackTest {
    @Test
    fun `should be able to get tract by its id each track has unique id`() {
        val track1 = Track(1)
        Assertions.assertEquals(track1.id, 1)
    }
    @Test
    fun `track should have the morning time slot inside`() {
        val track = Track(1)
        Assertions.assertNotNull(track.morning)
        Assertions.assertEquals(Constant.MORNING_DURATION, track.morning.length)
        Assertions.assertEquals(Constant.MORNING_START, track.morning.startTime)
        Assertions.assertEquals(Constant.MORNING_DURATION, track.morning.unassignedTimeLength)
    }
    @Test
    fun `track should have the afternoon time slot inside`() {
        val track = Track(1)
        Assertions.assertNotNull(track.afternoon)
        Assertions.assertEquals(Constant.AFTERNOON_DURATION + Constant.AFTERNOON_ALLOWABLE_DURATION, track.afternoon.length)
        Assertions.assertEquals(Constant.AFTERNOON_START, track.afternoon.startTime)
        Assertions.assertEquals(Constant.AFTERNOON_DURATION + Constant.AFTERNOON_ALLOWABLE_DURATION, track.afternoon.unassignedTimeLength)
    }
    @Test
    fun `should be able to transfer to string with expected format`() {
        val track1 = Track(1)
        Assertions.assertEquals("Track 1:", track1.toString())
    }
}
