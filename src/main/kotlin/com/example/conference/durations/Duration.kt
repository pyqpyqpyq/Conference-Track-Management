package com.example.conference.durations

abstract class Duration {
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
    abstract override fun toString(): String
    abstract fun toMinutes(): Int
}
