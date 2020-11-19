package com.example.conference.durations

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DurationTest {
    @Test
    fun `It should be able to get the minutes of duration When unit is minutes`() {
        val time = Minutes(30)
        Assertions.assertEquals(time.toMinutes(), 30)
    }
    @Test
    fun `It should be able to get the minutes of duration  When unit is lightning`() {
        val time = Lightning()
        Assertions.assertEquals(time.toMinutes(), 5)
    }
    @Test
    fun `It should be equals When unit is lightning and it is 5 minutes`() {
        val time1 = Lightning()
        val time2 = Minutes(5)
        Assertions.assertEquals(time1, time2)
    }
    @Test
    fun `It should be able to get the display of duration When unit is minutes`() {
        val time = Minutes(30)
        Assertions.assertEquals(time.toString(), "30min")
    }
    @Test
    fun `It should be able to get the display of duration When unit is lightning`() {
        val time = Lightning()
        Assertions.assertEquals(time.toString(), "lightning")
    }
}
