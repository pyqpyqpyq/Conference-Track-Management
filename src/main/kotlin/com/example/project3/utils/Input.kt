package com.example.project3.utils
import com.example.project3.model.Duration
import com.example.project3.model.TimeUnit
import com.example.project3.model.events.Talk
import java.io.File
import java.io.InputStream

class Input {
    fun read(): MutableList<Talk> {
        val inputStream: InputStream = File(Constant.INPUT_FILE_PATH).inputStream()
        val talkList = mutableListOf<Talk>()
        inputStream.bufferedReader().forEachLine { talkList.add(transferStringToTalk(it)) }
        return talkList
    }

    fun transferStringToDuration(input: String): Duration {
        return if (input == "lightning") {
            Duration(1, TimeUnit.LIGHTNING)
        } else {
            val num = input.replace(Regex("[^0-9]"), "").toInt()
            Duration(num, TimeUnit.MINUTES)
        }
    }

    fun transferStringToTalk(string: String): Talk {
        val wordList = string.split(' ')
        val name = wordList.subList(0, wordList.size - Constant.LAST_PART_REPRESENT_DURATION).joinToString(" ")
        val duration = transferStringToDuration(wordList[wordList.size - Constant.HUMAN_COMPUTER_DISTANCE])
        return Talk(name, duration)
    }
}