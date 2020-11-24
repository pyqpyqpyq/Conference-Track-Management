package com.example.conference.duration

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MinutesTest {
    @Test
    fun `It should be able to get the minutes of duration When unit is minutes`() {
        val time = Minutes(30)
        assertEquals(30, time.toMinutes())
    }

    @Test
    fun `It should be able to get the display of duration When unit is minutes`() {
        val time = Minutes(30)
        assertEquals("30min", time.toString())
    }
}
