package com.example.conference

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DurationTest {
    @Test
    fun `It should be able to get the base of duration `() {
        val time = Duration(30, TimeUnit.MINUTES)
        Assertions.assertEquals(time.base, 30)
    }
    @Test
    fun `It should be able to get the unit of duration `() {
        val time = Duration(30, TimeUnit.MINUTES)
        Assertions.assertEquals(time.unit, TimeUnit.MINUTES)
    }
    @Test
    fun `It should be able to get the minutes of duration When unit is minutes`() {
        val time = Duration(30, TimeUnit.MINUTES)
        Assertions.assertEquals(time.toInt(), 30)
    }
    @Test
    fun `It should be able to get the minutes of duration  When unit is lightning`() {
        val time = Duration(1, TimeUnit.LIGHTNING)
        Assertions.assertEquals(time.toInt(), 5)
    }
    @Test
    fun `It should be able to get the display of duration When unit is minutes`() {
        val time = Duration(30, TimeUnit.MINUTES)
        Assertions.assertEquals(time.toString(), "30min")
    }
    @Test
    fun `It should be able to get the display of duration When unit is lightning`() {
        val time = Duration(1, TimeUnit.LIGHTNING)
        Assertions.assertEquals(time.toString(), "lightning")
    }
}
