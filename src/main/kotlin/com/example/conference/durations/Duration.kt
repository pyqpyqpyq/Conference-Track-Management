package com.example.conference.durations

abstract class Duration {
    abstract fun toMinutes(): Int
    companion object {
        fun transferStringToDuration(input: String): Duration {
            return if (input == "lightning") {
                Lightning()
            } else {
                val num = input.replace(Regex("[^0-9]"), "").toInt()
                Minutes(num)
            }
        }
    }
}
