package com.example.conference.utils
import com.example.conference.constants.Constant
import java.io.File
import java.io.InputStream

class InputUtil {
    fun read(): MutableList<String> {
        val inputStream: InputStream = File(Constant.INPUT_FILE_PATH).inputStream()
        val talkListString = mutableListOf<String>()
        inputStream.bufferedReader().forEachLine { talkListString.add(it) }
        return talkListString
    }
}
