package com.example.conference.slot

import com.example.conference.constant.Constant

class Morning : Slot(startTime = Constant.MORNING_START, unassignedTimeLength = Constant.MORNING_DURATION) {
    val length = Constant.MORNING_DURATION
}
