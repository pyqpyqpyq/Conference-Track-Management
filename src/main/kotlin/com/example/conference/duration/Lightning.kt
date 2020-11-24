package com.example.conference.duration

class Lightning : Duration() {
    override fun toString(): String {
        return "lightning"
    }

    override fun toMinutes(): Int {
        return 5
    }
}
