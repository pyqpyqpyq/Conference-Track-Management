package com.example.conference.constants

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ConstantTest {
    @Test
    fun `should get the length of the morning and afternoon together with the allowable time `() {
        Assertions.assertEquals(Constant.MORNING_DURATION, 180)
        Assertions.assertEquals(Constant.AFTERNOON_DURATION, 180)
        Assertions.assertEquals(Constant.AFTERNOON_ALLOWABLE_DURATION, 60)
    }
}
