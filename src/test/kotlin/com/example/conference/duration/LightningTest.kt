package com.example.conference.duration

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LightningTest {
    @Test
    fun `It should be able to get the minutes of duration When unit is lightning`() {
        val time = Lightning()
        assertEquals(5, time.toMinutes())
    }
    @Test
    fun `It should be able to get the display of duration When unit is lightning`() {
        val time = Lightning()
        assertEquals("lightning", time.toString())
    }
}
