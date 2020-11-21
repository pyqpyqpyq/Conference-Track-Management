package com.example.conference.events

import com.example.conference.durations.Minutes
import com.example.conference.events.Talk.Companion.validateInput
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EventTest {
    @Test
    fun `should be able to transfer legal String to the Talk`() {
        val string = "Writing Fast Tests Against Enterprise Rails 60min"
        val name = "Writing Fast Tests Against Enterprise Rails"
        val timeUnit = Minutes(60)
        Assertions.assertEquals(Talk.transferStringToTalk(string), Talk(name, timeUnit))
    }
    @Test
    fun `two Talk with different name should not be equal `() {
        val name1 = "Writing Fast Tests Against Enterprise Rails"
        val duration1 = 60
        val name2 = "Overdoing it in Python"
        val duration2 = 45
        val talk1 = Talk(name1, Minutes(duration1))
        val talk2 = Talk(name2, Minutes(duration2))
        Assertions.assertNotEquals(talk1, talk2)
    }

    @Test
    fun `two Talk with same name but not different length should not be equal `() {
        val name1 = "Writing Fast Tests Against Enterprise Rails"
        val duration1 = 60
        val duration2 = 45
        val talk1 = Talk(name1, Minutes(duration1))
        val talk2 = Talk(name1, Minutes(duration2))
        Assertions.assertNotEquals(talk1, talk2)
    }
    @Test
    fun `Talk should not be equal to other class`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val talk1 = Talk(name, Minutes(duration))
        Assertions.assertNotEquals(talk1, 1)
    }
    @Test
    fun `Two talks with same name and length should be same`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val talk1 = Talk(name, Minutes(duration))
        val talk2 = Talk(name, Minutes(duration))
        Assertions.assertEquals(talk1, talk2)
    }
    @Test
    fun `should create a talk by giving name and the time duration of the event, and can get the name and time duration`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Minutes(duration))
        Assertions.assertEquals(event.name, "Writing Fast Tests Against Enterprise Rails")
        Assertions.assertEquals(event.duration.toMinutes(), 60)
    }
    @Test
    fun `should create a networkEvent by giving  the time duration of the event, and can get the name and time duration`() {
        val event = NetworkEvent()
        Assertions.assertEquals(event.name, "Networking Event")
    }
    @Test
    fun `should create a lunch time with length of 60 minutes and name is lunch`() {
        val event = Lunch()
        Assertions.assertEquals(event.name, "Lunch")
        Assertions.assertEquals(event.duration, Minutes(60))
    }
    @Test
    fun `should be able to toString to display for the talk`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = Minutes(60)
        val event = Talk(name, duration)
        Assertions.assertEquals(event.toString(), "Writing Fast Tests Against Enterprise Rails 60min")
    }
    @Test
    fun `should be able to return false if the input is not valid`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val nameList = mutableListOf<String>()
        nameList.add(name)
        Assertions.assertEquals(validateInput(), false)
    }
}
