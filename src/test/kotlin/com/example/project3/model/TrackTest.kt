package com.example.project3.model

import com.example.project3.model.events.Talk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TrackTest {
    @Test
    fun `should be able to create the object track (by id)which has the morning time slot, and it can put event inside`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, duration)
        val track = Track(1)
        track.morning.arrange(event)
        Assertions.assertEquals(track.morning.restLength, 120)
    }

    @Test
    fun `should be able to create the object track (by id)which has the afternoon time slot, and it can put event inside`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, duration)
        val track = Track(1)
        track.afternoon.arrange(event)
        Assertions.assertEquals(track.afternoon.restLength, 180)
    }
}
