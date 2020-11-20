package com.example.conference

import com.example.conference.durations.Minutes
import com.example.conference.events.Talk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TrackTest {
    @Test
    fun `should be able to get tract by its id each track has unique id`() {
        val track1 = Track(1)
        Assertions.assertEquals(track1.id, 1)
    }
    @Test
    fun `should be able to create the object track (by id)which has the morning time slot, and it can put event inside`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Minutes(duration))
        val track = Track(1)
        track.morning.arrange(event)
        Assertions.assertEquals(track.morning.restLength, 120)
    }

    @Test
    fun `should be able to create the object track (by id)which has the afternoon time slot, and it can put event inside`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Minutes(duration))
        val track = Track(1)
        track.afternoon.arrange(event)
        Assertions.assertEquals(track.afternoon.restLength, 180)
    }
    @Test
    fun `should be different if the id of the track is different`() {
        val track1 = Track(1)
        val track2 = Track(2)
        Assertions.assertNotEquals(track1, track2)
    }
    @Test
    fun `should be  different if compare track with other class`() {
        val track1 = Track(1)
        Assertions.assertNotEquals(track1, 1)
    }
    @Test
    fun `should be able to transfer to string with expected format`() {
        val track1 = Track(1)
        Assertions.assertEquals(track1.toString(),"Track 1:")
    }
}
