package com.example.conference.slot

import com.example.conference.constants.Constant.AFTERNOON_ALLOWABLE_DURATION
import com.example.conference.constants.Constant.AFTERNOON_DURATION
import com.example.conference.constants.Constant.AFTERNOON_START
import org.junit.jupiter.api.Test
import java.time.LocalTime
import kotlin.test.assertEquals

class AfternoonTest {
    @Test
    fun `The Afternoon should start from the 13pm`() {
        val afternoon = Afternoon()
        assertEquals(LocalTime.of(13, 0), afternoon.startTime)
    }
    @Test
    fun `The afternoon should be able 180min as the length ,and should use length as the init value of unassignedTimeLength `() {
        val afternoon = Afternoon()
        assertEquals(AFTERNOON_DURATION + AFTERNOON_ALLOWABLE_DURATION, afternoon.length)
        assertEquals(AFTERNOON_START, afternoon.startTime)
        assertEquals(AFTERNOON_DURATION + AFTERNOON_ALLOWABLE_DURATION, afternoon.unassignedTimeLength)
    }
    @Test
    fun `The Afternoon should use length as the init value `() {
        val afternoon = Afternoon()
        assertEquals(afternoon.unassignedTimeLength, afternoon.length)
    }
}
