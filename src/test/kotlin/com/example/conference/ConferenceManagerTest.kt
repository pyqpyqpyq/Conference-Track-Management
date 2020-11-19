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
        val tracks = mutableListOf<Track>()
        conference.addTrack(tracks)
        Assertions.assertNotNull(conference.getTrack(tracks, 1))
    }
    @Test
    fun `should be able to init a conference and it can be able to add tracks`() {
        val conference = ConferenceManager()
        val tracks = mutableListOf<Track>()
        conference.addTrack(tracks)
        Assertions.assertEquals(conference.getTrack(tracks, 1), Track(1))
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
        val tracks = mutableListOf<Track>()
        conference.addTrack(tracks)
        Assertions.assertEquals(conference.getAllSlots(tracks).size, 2)
        Assertions.assertEquals(conference.getAllSlots(tracks)[0], conference.getTrack(tracks, 1).morning)
        Assertions.assertEquals(conference.getAllSlots(tracks)[1], conference.getTrack(tracks, 1).afternoon)
        conference.addTrack(tracks)
        Assertions.assertEquals(conference.getAllSlots(tracks).size, 4)
    }
    @Test
    fun `should be able to get all the slots of the conference in Descending rate`() {
        val input = InputUtil()
        val conference = ConferenceManager()
        val tracks = mutableListOf<Track>()
        conference.addTrack(tracks)
        conference.addTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        conference.getTrack(tracks, 1).afternoon.arrange(input.transferStringToTalk(string1))
        conference.getTrack(tracks, 1).morning.arrange(input.transferStringToTalk(string2))
        val returnList = conference.rankSlots(conference.getAllSlotsByOrder(tracks))
        Assertions.assertEquals(returnList[0], conference.getTrack(tracks, 2).afternoon)
        Assertions.assertEquals(returnList[1], conference.getTrack(tracks, 1).afternoon)
        Assertions.assertEquals(returnList[2], conference.getTrack(tracks, 2).morning)
        Assertions.assertEquals(returnList[3], conference.getTrack(tracks, 1).morning)
    }
    @Test
    fun `should be able to put the first event to the first lots`() {
        val input = InputUtil()
        val tracks = mutableListOf<Track>()
        val conference = ConferenceManager()
        conference.addTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        talks.add(input.transferStringToTalk(string1))
        talks.add(input.transferStringToTalk(string2))
        conference.getAllSlotsByOrder(tracks)
        conference.arrangeOneTalk(talks, conference.getAllSlotsByOrder(tracks))
        Assertions.assertNotNull(conference.getTrack(tracks, 1).afternoon)
        Assertions.assertEquals(talks.size, 1)
    }
    @Test
    fun `should be able to put the first event to the first lots if successful return true`() {
        val input = InputUtil()
        val tracks = mutableListOf<Track>()
        val conference = ConferenceManager()
        conference.addTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        talks.add(input.transferStringToTalk(string1))
        talks.add(input.transferStringToTalk(string2))
        conference.getAllSlotsByOrder(tracks)
        Assertions.assertEquals(conference.arrangeOneTalk(talks, conference.getAllSlotsByOrder(tracks)), true)
    }
    @Test
    fun `should be able to put the first event to the first lots if unsuccessful return false`() {
        val input = InputUtil()
        val conference = ConferenceManager()
        val tracks = mutableListOf<Track>()
        conference.addTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 6000min"
        val talks = mutableListOf<Talk>()
        talks.add(input.transferStringToTalk(string1))
        talks.add(input.transferStringToTalk(string2))
        conference.getAllSlotsByOrder(tracks)
        Assertions.assertEquals(conference.arrangeOneTalk(talks, conference.getAllSlotsByOrder(tracks)), false)
    }
    @Test
    fun `if conferenceManager arrange should copy a input and origin input talks should be unmodified`() {
        val input = InputUtil()
        val conference = ConferenceManager()
        val string1 = "Communicating Over Distance 60min"
        val talks1 = mutableListOf<Talk>()
        val talks2 = mutableListOf<Talk>()
        talks1.add(input.transferStringToTalk(string1))
        talks2.add(input.transferStringToTalk(string1))
        conference.arrangeConferenceWithNTracks(talks1, 1)
        Assertions.assertEquals(talks1, talks2)
    }
    @Test
    fun `if conferenceManager arrange the talks at current tracks successful it should return the list of Track as the result output`() {
        val input = InputUtil()
        val conference = ConferenceManager()
        val string = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        repeat(50) { talks.add(input.transferStringToTalk(string)) }
        Assertions.assertEquals(conference.arrangeConferenceWithNTracks(talks, 1).size, 8)
    }
}
