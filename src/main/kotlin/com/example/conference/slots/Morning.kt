package com.example.conference.slots

import com.example.conference.constants.Constant
import java.time.LocalTime

class Morning : Slot() {
    override val startTime: LocalTime = LocalTime.of(9, 0)
    val length = Constant.MORNING_DURATION
    override var restLength = Constant.MORNING_DURATION
}
