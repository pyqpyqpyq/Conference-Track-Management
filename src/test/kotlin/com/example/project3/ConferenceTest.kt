package com.example.project3

import com.example.project3.model.Conference
import com.example.project3.model.Track
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ConferenceTest {
    @Test
    fun `should be able to init a conference and it can be able to add tracks`() {
        val conference = Conference()
        conference.addTrack()
        Assertions.assertEquals(conference.getTrack(1), Track(1))
    }
}
