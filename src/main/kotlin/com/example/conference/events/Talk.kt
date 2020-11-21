package com.example.conference.events

import com.example.conference.constants.Constant
import com.example.conference.durations.Duration
import com.example.conference.durations.Duration.Companion.transferStringToDuration
import java.util.regex.Pattern

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
        fun validateInput(input: MutableList<String>): Boolean {
            for (index in 0 until input.size) {
                if (!Pattern.matches(Constant.INPUT_VALIDATED, input[index])) { return false }
            }
            return true
        }
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
        fun transferStringListToTalkList(input: MutableList<String>): MutableList<Talk> {
            val talkList = mutableListOf<Talk>()
            input.stream().forEach { talkList.add(transferStringToTalk(it)) }
            return talkList
        }
    }
}
