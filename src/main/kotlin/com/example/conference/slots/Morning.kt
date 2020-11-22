package com.example.conference.slots

import com.example.conference.constants.Constant
import java.time.LocalTime

class Morning : Slot(startTime = LocalTime.of(9, 0), unassignedTimeLength = Constant.MORNING_DURATION) {
    val length = Constant.MORNING_DURATION
    init {
        addedTime.add(startTime)
    }
}
