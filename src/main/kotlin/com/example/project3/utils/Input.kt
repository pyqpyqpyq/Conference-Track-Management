package com.example.project3.utils
import java.io.File
import java.io.InputStream

class Input {
    fun read(): MutableList<String> {
        val inputStream: InputStream = File(Constant.INPUT_FILE_PATH).inputStream()
        val lineList = mutableListOf<String>()
        inputStream.bufferedReader().forEachLine { lineList.add(it) }
        return lineList
    }
}
