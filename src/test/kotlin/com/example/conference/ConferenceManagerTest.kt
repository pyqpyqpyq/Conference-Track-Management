package com.example.conference

import com.example.conference.constants.Constant
import com.example.conference.events.Talk
import com.example.conference.slots.Afternoon
import com.example.conference.slots.Morning
import com.example.conference.utils.InputUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ConferenceManagerTest {
    @Test
    fun `should init a track when the conference is created`() {
        val conference = ConferenceManager()
        Assertions.assertNotNull(conference.getTrack(1))
    }
    @Test
    fun `should be able to init a conference and it can be able to add tracks`() {
        val conference = ConferenceManager()
        conference.addTrack()
        Assertions.assertEquals(conference.getTrack(1), Track(1))
    }
    @Test
    fun `should be able to rank the list of talks by their length in descending, so the shortest event should be in the last`() {
        val input = InputUtil()
        val conference = ConferenceManager()
        val inputList = input.read()
        val string = "Rails for Python Developers lightning"
        conference.rankTalks(inputList)
        Assertions.assertEquals(inputList[inputList.size - Constant.HUMAN_COMPUTER_DISTANCE], input.transferStringToTalk(string))
    }
    @Test
    fun `should be able to rank the list of slots by their length in descending, so the shortest event should be in the last`() {
        val conference = ConferenceManager()
        val input = InputUtil()
        val morning1 = Morning()
        val morning2 = Morning()
        val string = "Rails for Python Developers lightning"
        morning1.arrange(input.transferStringToTalk(string))
        val afternoon = Afternoon()
        val slotList = mutableListOf(morning1, morning2, afternoon)
        conference.rankSlots(slotList)
        Assertions.assertEquals(slotList[0], afternoon)
        Assertions.assertEquals(slotList[1], morning2)
        Assertions.assertEquals(slotList[2], morning1)
    }
    @Test
    fun `should be able to get all the slots of the conference`() {
        val conference = ConferenceManager()
        Assertions.assertEquals(conference.getAllSlots().size, 2)
        Assertions.assertEquals(conference.getAllSlots()[0], conference.getTrack(1).morning)
        Assertions.assertEquals(conference.getAllSlots()[1], conference.getTrack(1).afternoon)
        conference.addTrack()
        Assertions.assertEquals(conference.getAllSlots().size, 4)
    }
    @Test
    fun `should be able to get all the slots of the conference in Descending rate`() {
        val input = InputUtil()
        val conference = ConferenceManager()
        conference.addTrack()
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        conference.getTrack(1).afternoon.arrange(input.transferStringToTalk(string1))
        conference.getTrack(1).morning.arrange(input.transferStringToTalk(string2))
        val returnList = conference.rankSlots(conference.getAllSlotsByOrder())
        Assertions.assertEquals(returnList[0], conference.getTrack(2).afternoon)
        Assertions.assertEquals(returnList[1], conference.getTrack(1).afternoon)
        Assertions.assertEquals(returnList[2], conference.getTrack(2).morning)
        Assertions.assertEquals(returnList[3], conference.getTrack(1).morning)
    }
    @Test
    fun `should be able to put the first event to the first lots`() {
        val input = InputUtil()
        val conference = ConferenceManager()
        conference.addTrack()
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        talks.add(input.transferStringToTalk(string1))
        talks.add(input.transferStringToTalk(string2))
        conference.getAllSlotsByOrder()
        conference.arrangeOneTalk(talks, conference.getAllSlotsByOrder())
        Assertions.assertNotNull(conference.getTrack(1).afternoon)
        Assertions.assertEquals(talks.size, 1)
    }
    @Test
    fun `should be able to put the first event to the first lots if successful return true`() {
        val input = InputUtil()
        val conference = ConferenceManager()
        conference.addTrack()
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        talks.add(input.transferStringToTalk(string1))
        talks.add(input.transferStringToTalk(string2))
        conference.getAllSlotsByOrder()
        Assertions.assertEquals(conference.arrangeOneTalk(talks, conference.getAllSlotsByOrder()), true)
    }

    @Test
    fun `should be able to arrange all talks when given a small list of talks`() {
        val input = InputUtil()
        val conference = ConferenceManager()
        conference.addTrack()
        val string1 = "Writing Fast Tests Against Enterprise Rails 60min"
        val string2 = "Overdoing it in Python 45min"
        val string3 = "Lua for the Masses 30min"
        val string4 = "Ruby Errors from Mismatched Gem Versions 45min"
        val talks = mutableListOf<Talk>()
        talks.add(input.transferStringToTalk(string1))
        talks.add(input.transferStringToTalk(string2))
        talks.add(input.transferStringToTalk(string3))
        talks.add(input.transferStringToTalk(string4))
        conference.arrangeConference(talks)
        Assertions.assertEquals(talks.size, 0)
        Assertions.assertNotEquals(conference.getTrack(1).morning.restLength, 180)
        Assertions.assertNotEquals(conference.getTrack(1).morning.restLength, 240)
    }
}
