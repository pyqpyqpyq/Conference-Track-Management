package com.example.conference.constant

import java.time.LocalTime

object Constant {
    const val ONE_MORE_TRACK = 1
    const val MORNING_DURATION = 180
    const val AFTERNOON_DURATION = 180
    const val AFTERNOON_ALLOWABLE_DURATION = 60
    const val HUMAN_COMPUTER_DISTANCE = 1
    const val LAST_PART_REPRESENT_DURATION = 1
    const val RGX_END_WITH_TIME = ".*(lightning|([1-9][0-9]*)min)\$"
    const val RGX_IF_CONTAIN_DIGIT = ".*[0-9]+.*"
    const val INVALID_INPUT_TIME_FORMAT = "Invalid Input For Time Format, Please Check And Try Again!"
    const val INVALID_INPUT_CONTAIN_DIGIT = "Invalid Input Contains Digits In Name, Please Check, And Try Again!"
    const val VALID_INPUT_PATH = "src/main/resources/inputForTest/validInput.txt"
    const val UNEXPECTED_CASE_PATH = "src/main/resources/inputForTest/unexpectedInput.txt"
    const val NAME_CONTAIN_DIGIT_FILE_PATH = "src/main/resources/inputForTest/nameContainDigitInput.txt"
    const val TIME_INVALID_FILE_PATH = "src/main/resources/inputForTest/timeFormatInvalidInput.txt"
    const val CAN_NOT_BE_FOUND_PATH = "ssrc/main/resources/input.txt"
    const val File_NOT_FOUND = "Can not find File, Please Check And Try Again!"
    const val UNEXPECTED_EXCEPTION = "Unexpected Exception!"
    const val PRESS_ENTER = "--------------Enter a Path, And Press Enter To Continue--------------"
    val MORNING_START: LocalTime = LocalTime.of(9, 0)
    val AFTERNOON_START: LocalTime = LocalTime.of(13, 0)
    val LUNCH_TIME: LocalTime = LocalTime.of(12, 0)
    val NETWORK_EVENT_TIME: LocalTime = LocalTime.of(17, 0)
}
