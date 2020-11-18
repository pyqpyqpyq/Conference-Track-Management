package com.example.conference.slots

import com.example.conference.constants.Constant

class Morning : Slot() {
    val length = Constant.MORNING_DURATION
    override var restLength = Constant.MORNING_DURATION
}
