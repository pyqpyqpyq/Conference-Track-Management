package com.example.conference.durations

class Minutes(private val base: Int) : Duration() {
    override fun toString(): String {
        return base.toString() + "min"
    }
    override fun toMinutes(): Int {
        return base
    }
//    override fun equals(other: Any?): Boolean {
//        return when (other) {
//            is Lightning -> base == 5
//            is Minutes -> base == other.base
//            else -> false
//        }
//    }
}
