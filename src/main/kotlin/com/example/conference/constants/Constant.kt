package com.example.conference.constants

import java.time.LocalTime

class Constant {
    companion object {
        const val ONE_MORE_TRACK = 1
        const val MORNING_DURATION = 180
        const val AFTERNOON_DURATION = 180
        const val AFTERNOON_ALLOWABLE_DURATION = 60
        const val HUMAN_COMPUTER_DISTANCE = 1
        const val LAST_PART_REPRESENT_DURATION = 1
        const val INPUT_FILE_PATH = "src/main/resources/input.txt"
        const val RGX_END_WITH_TIME = ".*(lightning|([1-9][0-9]*)min)\$"
        const val RGX_IF_CONTAIN_DIGIT = ".*[0-9]+.*"
        const val INVALID_INPUT = "Invalid Input Please Check, And Try Again!"
        const val PRESS_ENTER = "--------------Press Enter To Continue--------------"
        val LUNCH_TIME: LocalTime = LocalTime.of(12, 0)
        val NETWORK_EVENT_TIME: LocalTime = LocalTime.of(17, 0)
    }
}
