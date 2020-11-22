package com.example.conference.slots

import com.example.conference.constants.Constant.Companion.AFTERNOON_ALLOWABLE_DURATION
import com.example.conference.constants.Constant.Companion.AFTERNOON_DURATION
import com.example.conference.constants.Constant.Companion.AFTERNOON_START
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalTime

class AfternoonTest {
    @Test
    fun `The Afternoon should start from the 13pm`() {
        val afternoon = Afternoon()
        Assertions.assertEquals(LocalTime.of(13, 0), afternoon.startTime)
    }
    @Test
    fun `The afternoon should be able 180min as the length ,and should use length as the init value of unassignedTimeLength `() {
        val afternoon = Afternoon()
        Assertions.assertEquals(AFTERNOON_DURATION + AFTERNOON_ALLOWABLE_DURATION, afternoon.length)
        Assertions.assertEquals(AFTERNOON_START, afternoon.startTime)
        Assertions.assertEquals(AFTERNOON_DURATION + AFTERNOON_ALLOWABLE_DURATION, afternoon.unassignedTimeLength)
    }
    @Test
    fun `The Afternoon should use length as the init value `() {
        val afternoon = Afternoon()
        Assertions.assertEquals(afternoon.unassignedTimeLength, afternoon.length)
    }
}
