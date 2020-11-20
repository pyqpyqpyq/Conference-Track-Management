package com.example.conference.events

import com.example.conference.constants.Constant
import com.example.conference.durations.Duration
import com.example.conference.durations.Duration.Companion.transferStringToDuration

class Talk(val name: String, val duration: Duration) : Event() {
    override fun toString(): String {
        return "$name $duration"
    }
    override fun equals(other: Any?): Boolean {
        return when (other) {
            !is Talk -> false
            else -> name == other.name && duration == other.duration
        }
    }
    companion object {
        fun transferStringToTalk(string: String): Talk {
            val wordList = string.split(' ')
            val name = wordList.subList(0, wordList.size - Constant.LAST_PART_REPRESENT_DURATION).joinToString(" ")
            val duration = transferStringToDuration(wordList[wordList.size - Constant.HUMAN_COMPUTER_DISTANCE])
            return Talk(name, duration)
        }
        fun rankTalks(inputList: MutableList<Talk>): MutableList<Talk> {
            inputList.sortByDescending { it.duration.toMinutes() }
            return inputList
        }
    }
}
