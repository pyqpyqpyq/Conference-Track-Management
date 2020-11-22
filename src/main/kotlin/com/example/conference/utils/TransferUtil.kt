package com.example.conference.utils

import com.example.conference.constants.Constant
import com.example.conference.duration.Duration
import com.example.conference.duration.Lightning
import com.example.conference.duration.Minutes
import com.example.conference.event.Talk

object TransferUtil {
    fun transferStringToDuration(input: String): Duration {
        return if (input == "lightning") {
            Lightning()
        } else {
            val num = input.replace(Regex("[^0-9]"), "").toInt()
            Minutes(num)
        }
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
