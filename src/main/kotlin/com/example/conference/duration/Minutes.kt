package com.example.conference.duration

class Minutes(private val base: Int) : Duration() {
    override fun toString(): String {
        return base.toString() + "min"
    }

    override fun toMinutes(): Int {
        return base
    }
}
