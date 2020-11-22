package com.example.conference.duration

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DurationTest {
    @Test
    fun `It should not be able to transfer to minutes for the lightning and Minutes`() {
        val time1 = Minutes(3)
        val time2 = Lightning()
        assertEquals(3, time1.toMinutes())
        assertEquals(5, time2.toMinutes())
    }
}
