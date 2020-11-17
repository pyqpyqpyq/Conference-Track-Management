package com.example.project3.model.conferences

import com.example.project3.model.events.Event
import com.example.project3.utils.Constant

class Morning(): Slot() {
    var length = Constant.MORNING_DURATION
    override var restTime = Constant.MORNING_DURATION
}
