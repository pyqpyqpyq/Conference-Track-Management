package com.example.conference

import com.example.conference.Track.Companion.addOneMoreTrack
import com.example.conference.Track.Companion.getAllSlotsByOrder
import com.example.conference.durations.Minutes
import com.example.conference.events.Talk
import com.example.conference.events.Talk.Companion.transferStringListToTalkList
import com.example.conference.events.Talk.Companion.transferStringToTalk
import com.example.conference.slots.Afternoon
import com.example.conference.slots.Morning
import com.example.conference.slots.Slot.Companion.arrangeOneTalk
import com.example.conference.slots.Slot.Companion.rankSlots
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ConferenceManagerTest {
    @Test
    fun `should be able to read the file as input`() {
        val conferenceManager = ConferenceManager()
        conferenceManager.readFromFile().forEach { println(it) }
    }
    @Test
    fun `should be able to transfer StringList to the TalkList`() {
        val string = "Writing Fast Tests Against Enterprise Rails 60min"
        val stringList = mutableListOf<String>()
        val talkList = mutableListOf<Talk>()
        repeat(5) { stringList.add(string) }
        Assertions.assertEquals(transferStringListToTalkList(stringList).javaClass, talkList.javaClass)
    }
    @Test
    fun `should init a track when the conference is created`() {
        val tracks = mutableListOf<Track>()
        addOneMoreTrack(tracks)
        Assertions.assertNotNull(tracks)
    }
    @Test
    fun `should be able to rank the list of slots by their length in descending, so the shortest event should be in the last`() {
        val morning1 = Morning()
        val morning2 = Morning()
        val string = "Rails for Python Developers lightning"
        morning1.arrange(transferStringToTalk(string))
        val afternoon = Afternoon()
        val slotList = mutableListOf(morning1, morning2, afternoon)
        rankSlots(slotList)
        Assertions.assertEquals(slotList[0], afternoon)
        Assertions.assertEquals(slotList[1], morning2)
        Assertions.assertEquals(slotList[2], morning1)
    }
    @Test
    fun `should be able to get all the slots of the conference in Descending rate`() {
        val tracks = mutableListOf<Track>()
        addOneMoreTrack(tracks)
        addOneMoreTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        tracks[0].afternoon.arrange(transferStringToTalk(string1))
        tracks[1].morning.arrange(transferStringToTalk(string2))
        val returnList = rankSlots(getAllSlotsByOrder(tracks))
        Assertions.assertEquals(returnList[0], tracks[1].afternoon)
        Assertions.assertEquals(returnList[1], tracks[0].afternoon)
        Assertions.assertEquals(returnList[2], tracks[0].morning)
        Assertions.assertEquals(returnList[3], tracks[1].morning)
    }
    @Test
    fun `should be able to put the first event to the first lots`() {
        val tracks = mutableListOf<Track>()
        addOneMoreTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        talks.add(transferStringToTalk(string1))
        talks.add(transferStringToTalk(string2))
        getAllSlotsByOrder(tracks)
        arrangeOneTalk(talks, getAllSlotsByOrder(tracks))
        Assertions.assertNotNull(tracks[0].afternoon)
        Assertions.assertEquals(talks.size, 1)
    }
    @Test
    fun `should be able to put the first event to the first lots if successful return true`() {
        val tracks = mutableListOf<Track>()
        addOneMoreTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        talks.add(transferStringToTalk(string1))
        talks.add(transferStringToTalk(string2))
        getAllSlotsByOrder(tracks)
        Assertions.assertEquals(arrangeOneTalk(talks, getAllSlotsByOrder(tracks)), true)
    }
    @Test
    fun `should be able to put the first event to the first lots if unsuccessful return false`() {
        val tracks = mutableListOf<Track>()
        addOneMoreTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance"
        val duration = Minutes(Int.MAX_VALUE)
        val talks = mutableListOf<Talk>()
        talks.add(transferStringToTalk(string1))
        talks.add(Talk(string2, duration))
        getAllSlotsByOrder(tracks)
        Assertions.assertEquals(arrangeOneTalk(talks, getAllSlotsByOrder(tracks)), false)
    }
    @Test
    fun `if conferenceManager arrange should copy a input and origin input talks should be unmodified`() {
        val conference = ConferenceManager()
        val string1 = "Communicating Over Distance 60min"
        val talks1 = mutableListOf<Talk>()
        val talks2 = mutableListOf<Talk>()
        talks1.add(transferStringToTalk(string1))
        talks2.add(transferStringToTalk(string1))
        conference.arrangeConferenceWithNTracks(talks1, 1)
        Assertions.assertEquals(talks1.size, talks2.size)
    }
    @Test
    fun `if conferenceManager arrange the talks at current tracks successful it should return the list of Track as the result output`() {
        val conference = ConferenceManager()
        val string = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        repeat(50) { talks.add(transferStringToTalk(string)) }
        Assertions.assertEquals(conference.arrangeConferenceWithNTracks(talks, 1).size, 8)
    }
    @Test
    fun `conferenceManager's method of arrangeConference can accept input of string and transfer string to talks and arrange talks then output the arranged tracks`() {
        val conference = ConferenceManager()
        conference.arrangeConference()
    }
    @Test
    fun `should be able to output the result`() {
        val conferenceManager = ConferenceManager()
        val conference = ConferenceManager()
        val string = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        repeat(50) { talks.add(transferStringToTalk(string)) }
        val result = conference.arrangeConferenceWithNTracks(talks, 1)
        conferenceManager.outputToConsole(result)
    }
    @Test
    fun `should be able to return "Invalid input please check again" if the invalid input`() {
        val conferenceManager = ConferenceManager()
        val string = "Communicating Over Distance 60min invalid"
        val talks = mutableListOf<String>()
        talks.add(string)
        conferenceManager.validate(talks)
    }
}
