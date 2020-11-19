package com.example.conference.slots

import com.example.conference.constants.Constant
import java.time.LocalTime

class Afternoon : Slot() {
    override val startTime: LocalTime = LocalTime.of(13, 0)
    val length = Constant.AFTERNOON_ALLOWABLE_DURATION + Constant.AFTERNOON_DURATION
    override var restLength = Constant.AFTERNOON_ALLOWABLE_DURATION + Constant.AFTERNOON_DURATION
    init {
        addedTime.add(startTime)
    }
}
