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
    fun `It should not be equals When two duration of minutes of different length`() {
        val time1 = Minutes(6)
        val time2 = Minutes(5)
        Assertions.assertNotEquals(time1, time2)
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
    @Test
    fun `It should not be same for two time in lightning and another in minutes but not in 5 minutes`() {
        val time1 = Minutes(3)
        val time2 = Lightning()
        Assertions.assertNotEquals(time1, time2)
    }
    @Test
    fun `It should not be same for one time and one other than duration`() {
        val time1 = Minutes(5)
        val time2 = 5
        Assertions.assertNotEquals(time1, time2)
    }
}
