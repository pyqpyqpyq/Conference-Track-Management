package com.example.conference.slots

import com.example.conference.constants.Constant

class Afternoon : Slot() {
    var length = Constant.AFTERNOON_ALLOWABLE_DURATION + Constant.AFTERNOON_DURATION
    override var restLength = Constant.AFTERNOON_ALLOWABLE_DURATION + Constant.AFTERNOON_DURATION
}
