package com.example.conference.slot

import com.example.conference.constant.Constant
import com.example.conference.constant.Constant.MORNING_DURATION
import org.junit.jupiter.api.Test
import java.time.LocalTime
import kotlin.test.assertEquals

class MorningTest {
    @Test
    fun `The Morning should start from the 9am`() {
        val morning = Morning()
        assertEquals(LocalTime.of(9, 0), morning.startTime)
    }
    @Test
    fun `The morning should be able 180min as the length ,and should use length as the init value of unassignedTimeLength `() {
        val morning = Morning()
        assertEquals(MORNING_DURATION, morning.length)
        assertEquals(Constant.MORNING_START, morning.startTime)
        assertEquals(MORNING_DURATION, morning.unassignedTimeLength)
    }
    @Test
    fun `The morning should use length as the init value `() {
        val morning = Morning()
        assertEquals(morning.unassignedTimeLength, morning.length)
    }
}
