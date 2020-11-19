package com.example.conference.durations

class Minutes(private val base: Int) : Duration() {
    override fun toString(): String {
        return base.toString() + "min"
    }
    override fun toMinutes(): Int {
        return base
    }
    override fun equals(other: Any?): Boolean {
        return when (other) {
            !is Minutes -> false
            else ->
                this === other ||
                    base == other.base
        }
    }
}
