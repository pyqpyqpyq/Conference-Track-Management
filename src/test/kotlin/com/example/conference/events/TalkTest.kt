package com.example.conference.events

import com.example.conference.durations.Minutes
import com.example.conference.events.Talk.Companion.rankTalks
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TalkTest {
    @Test
    fun `should create a talk by giving name and the time duration of the event, and can get the name and time duration`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Minutes(duration))
        Assertions.assertEquals(event.name, "Writing Fast Tests Against Enterprise Rails")
        Assertions.assertEquals(60, event.duration.toMinutes())
    }
    @Test
    fun `should order the talks by desc given a list of talks`() {
        val name1 = "Writing Fast Tests Against Enterprise Rails"
        val duration1 = Minutes(30)
        val talk1 = Talk(name1, duration1)
        val name2 = "Lua for the Masses"
        val duration2 = Minutes(60)
        val talk2 = Talk(name2, duration2)
        val talkList = mutableListOf(talk1, talk2)
        rankTalks(talkList)
        Assertions.assertEquals(talk2, talkList[0])
        Assertions.assertEquals(talk1, talkList[1])
    }
}
