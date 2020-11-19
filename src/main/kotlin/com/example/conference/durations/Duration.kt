package com.example.conference.durations

abstract class Duration() {
    abstract override fun toString(): String
    abstract fun toMinutes(): Int
}
