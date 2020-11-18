package com.example.project3.utils
import com.example.project3.model.Duration
import com.example.project3.model.TimeUnit
import com.example.project3.model.events.Talk
import java.io.File
import java.io.InputStream

class Input {
    fun read(): MutableList<String> {
        val inputStream: InputStream = File(Constant.INPUT_FILE_PATH).inputStream()
        val lineList = mutableListOf<String>()
        inputStream.bufferedReader().forEachLine { lineList.add(it) }
        return lineList
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
        var wordList = string.split(' ')
        var name = wordList.subList(0, wordList.size - Constant.LAST_PART_REPRESENT_DURATION).joinToString(" ")
        var duration = transferStringToDuration(wordList[wordList.size - Constant.HUMAN_COMPUTER_DISTANCE])
        return Talk(name, duration)
    }
}
