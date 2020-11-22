package com.example.conference.slots

import com.example.conference.constants.Constant
import com.example.conference.constants.Constant.Companion.MORNING_DURATION
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalTime

class MorningTest {
    @Test
    fun `The Morning should start from the 9am`() {
        val morning = Morning()
        Assertions.assertEquals(LocalTime.of(9, 0), morning.startTime)
    }
    @Test
    fun `The morning should be able 180min as the length ,and should use length as the init value of unassignedTimeLength `() {
        val morning = Morning()
        Assertions.assertEquals(MORNING_DURATION, morning.length)
        Assertions.assertEquals(Constant.MORNING_START, morning.startTime)
        Assertions.assertEquals(MORNING_DURATION, morning.unassignedTimeLength)
    }
    @Test
    fun `The morning should use length as the init value `() {
        val morning = Morning()
        Assertions.assertEquals(morning.unassignedTimeLength, morning.length)
    }
}
