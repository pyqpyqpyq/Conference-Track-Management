package com.example.conference.slots

import com.example.conference.constants.Constant

class Morning : Slot(startTime = Constant.MORNING_START, unassignedTimeLength = Constant.MORNING_DURATION) {
    val length = Constant.MORNING_DURATION
}
