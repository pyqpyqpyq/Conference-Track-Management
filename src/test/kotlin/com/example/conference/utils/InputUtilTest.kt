package com.example.conference.utils

import com.example.conference.ConferenceManager
import org.junit.jupiter.api.Test

class InputUtilTest {
    @Test
    fun `should be able to read the file as input`() {
        val conferenceManager = ConferenceManager()
        conferenceManager.read().forEach { println(it) }
    }
}
