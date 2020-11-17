package com.example.project3.model.events

class Talk(var name: String, var duration: Int) : Event() {
    override fun name(): String {
        return name
    }
    override fun duration(): Int {
        return duration
    }
}
