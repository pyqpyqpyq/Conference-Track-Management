package com.example.project3.model.events

import com.example.project3.model.Duration

data class Talk(var name: String, var duration: Duration) : Event() {
    override fun name(): String {
        return name
    }
    override fun duration(): Int {
        return duration.toInt()
    }
}
