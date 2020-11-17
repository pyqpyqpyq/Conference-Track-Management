package com.example.project3.model.events

class Talk(var name: String, var duration: String) : Event() {
    override fun name(): String {
        return name
    }
    override fun duration(): String {
        return duration
    }
}
