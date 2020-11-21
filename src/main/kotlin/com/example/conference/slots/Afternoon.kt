package com.example.conference.slots

import com.example.conference.constants.Constant
import java.time.LocalTime

class Afternoon : Slot(startTime = LocalTime.of(13, 0), unassignedTimeLength = Constant.AFTERNOON_ALLOWABLE_DURATION + Constant.AFTERNOON_DURATION) {
    val length = Constant.AFTERNOON_ALLOWABLE_DURATION + Constant.AFTERNOON_DURATION
    init {
        addedTime.add(startTime)
    }
}
