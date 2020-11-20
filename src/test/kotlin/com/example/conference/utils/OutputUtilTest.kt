package com.example.conference.utils

import com.example.conference.ConferenceManager
import com.example.conference.events.Talk
import com.example.conference.events.Talk.Companion.transferStringToTalk
import org.junit.jupiter.api.Test

class OutputUtilTest {
    @Test
    fun `should be able to read the file as input`() {
        val conferenceManager = ConferenceManager()
        val conference = ConferenceManager()
        val string = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        repeat(50) { talks.add(transferStringToTalk(string)) }
        val result = conference.arrangeConferenceWithNTracks(talks, 1)
        conferenceManager.output(result)
    }
}
