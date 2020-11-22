package com.example.conference.event

import com.example.conference.duration.Duration

class Talk(name: String, val duration: Duration) : Event(name) {
    override fun toString(): String {
        return "$name $duration"
    }
    companion object {
        fun rankTalks(inputList: MutableList<Talk>): MutableList<Talk> {
            inputList.sortByDescending { it.duration.toMinutes() }
            return inputList
        }
    }
}
