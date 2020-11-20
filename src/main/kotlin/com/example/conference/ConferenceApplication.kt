package com.example.conference

import com.example.conference.utils.InputUtil.Companion.read
import com.example.conference.utils.OutputUtil.Companion.output

fun main() {
    val input = read()
    val conferenceManager = ConferenceManager()
    val result = conferenceManager.arrangeConference(input)
    output(result)
}
