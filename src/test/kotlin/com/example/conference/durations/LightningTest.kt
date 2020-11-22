package com.example.conference.durations

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LightningTest {
    @Test
    fun `It should be able to get the minutes of duration When unit is lightning`() {
        val time = Lightning()
        Assertions.assertEquals(5, time.toMinutes())
    }
    @Test
    fun `It should be able to get the display of duration When unit is lightning`() {
        val time = Lightning()
        Assertions.assertEquals("lightning", time.toString())
    }
}
