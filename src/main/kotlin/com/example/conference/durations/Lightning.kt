package com.example.conference.durations

class Lightning : Duration() {
    override fun toString(): String {
        return "lightning"
    }
    override fun toMinutes(): Int {
        return 5
    }
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Lightning -> true
            else ->
                Minutes(5) == other
        }
    }
}
