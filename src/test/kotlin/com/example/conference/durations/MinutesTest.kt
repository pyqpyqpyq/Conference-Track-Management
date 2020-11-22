package com.example.conference.durations

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MinutesTest {
    @Test
    fun `It should be able to get the minutes of duration When unit is minutes`() {
        val time = Minutes(30)
        Assertions.assertEquals(30, time.toMinutes())
    }
    @Test
    fun `It should be able to get the display of duration When unit is minutes`() {
        val time = Minutes(30)
        Assertions.assertEquals("30min", time.toString())
    }
}
