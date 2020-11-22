package com.example.conference.durations

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DurationTest {
    @Test
    fun `It should not be able to transfer to minutes for the lightning and Minutes`() {
        val time1 = Minutes(3)
        val time2 = Lightning()
        Assertions.assertEquals(3, time1.toMinutes())
        Assertions.assertEquals(5, time2.toMinutes())
    }
}
