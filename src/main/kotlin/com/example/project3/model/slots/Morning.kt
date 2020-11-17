package com.example.project3.model.slots

import com.example.project3.utils.Constant

class Morning : Slot() {
    val length = Constant.MORNING_DURATION
    override var restLength = Constant.MORNING_DURATION
}
