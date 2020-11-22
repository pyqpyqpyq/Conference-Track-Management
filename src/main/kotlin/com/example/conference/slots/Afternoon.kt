package com.example.conference.slots

import com.example.conference.constants.Constant

class Afternoon : Slot(startTime = Constant.AFTERNOON_START, unassignedTimeLength = Constant.AFTERNOON_ALLOWABLE_DURATION + Constant.AFTERNOON_DURATION) {
    val length = Constant.AFTERNOON_ALLOWABLE_DURATION + Constant.AFTERNOON_DURATION
}
