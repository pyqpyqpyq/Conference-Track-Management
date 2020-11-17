package com.example.project3.model.slots

import com.example.project3.utils.Constant

class Afternoon : Slot() {
    var length = Constant.AFTERNOON_ALLOWABLE_DURATION + Constant.AFTERNOON_DURATION
    override var restTime = Constant.AFTERNOON_ALLOWABLE_DURATION + Constant.AFTERNOON_DURATION
}
