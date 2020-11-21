package com.example.conference.events

import com.example.conference.constants.Constant
import com.example.conference.durations.Duration
import com.example.conference.durations.Duration.Companion.transferStringToDuration

class Talk(name: String, val duration: Duration) : Event(name) {
    override fun toString(): String {
        return "$name $duration"
    }
    companion object {
        fun rankTalks(inputList: MutableList<Talk>): MutableList<Talk> {
            inputList.sortByDescending { it.duration.toMinutes() }
            return inputList
        }
        fun transferStringToTalk(string: String): Talk {
            val wordList = string.split(' ')
            val name = wordList.subList(0, wordList.size - Constant.LAST_PART_REPRESENT_DURATION).joinToString(" ")
            val duration = transferStringToDuration(wordList[wordList.size - Constant.HUMAN_COMPUTER_DISTANCE])
            return Talk(name, duration)
        }
        fun transferStringListToTalkList(input: MutableList<String>): MutableList<Talk> {
            val talkList = mutableListOf<Talk>()
            input.stream().forEach { talkList.add(transferStringToTalk(it)) }
            return talkList
        }
    }
}
