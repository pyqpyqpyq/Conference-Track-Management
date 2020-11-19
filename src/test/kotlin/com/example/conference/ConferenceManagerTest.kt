package com.example.conference

import com.example.conference.durations.Lightning
import com.example.conference.durations.Minutes
import com.example.conference.events.Talk
import com.example.conference.slots.Afternoon
import com.example.conference.slots.Morning
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ConferenceManagerTest {
    @Test
    fun `should be able to transfer String to the Duration`() {
        val conferenceManager = ConferenceManager()
        val string1 = "60min"
        val string2 = "lightning"
        Assertions.assertEquals(conferenceManager.transferStringToDuration(string1), Minutes(60))
        Assertions.assertEquals(conferenceManager.transferStringToDuration(string2), Lightning())
    }
    @Test
    fun `should be able to transfer legal String to the Talk`() {
        val conferenceManager = ConferenceManager()
        val string = "Writing Fast Tests Against Enterprise Rails 60min"
        val name = "Writing Fast Tests Against Enterprise Rails"
        val timeUnit = Minutes(60)
        Assertions.assertEquals(conferenceManager.transferStringToTalk(string), Talk(name, timeUnit))
    }
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
    fun `should be able to rank the list of slots by their length in descending, so the shortest event should be in the last`() {
        val conference = ConferenceManager()
        val morning1 = Morning()
        val morning2 = Morning()
        val string = "Rails for Python Developers lightning"
        morning1.arrange(conference.transferStringToTalk(string))
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
        val conference = ConferenceManager()
        val tracks = mutableListOf<Track>()
        conference.addTrack(tracks)
        conference.addTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        conference.getTrack(tracks, 1).afternoon.arrange(conference.transferStringToTalk(string1))
        conference.getTrack(tracks, 1).morning.arrange(conference.transferStringToTalk(string2))
        val returnList = conference.rankSlots(conference.getAllSlotsByOrder(tracks))
        Assertions.assertEquals(returnList[0], conference.getTrack(tracks, 2).afternoon)
        Assertions.assertEquals(returnList[1], conference.getTrack(tracks, 1).afternoon)
        Assertions.assertEquals(returnList[2], conference.getTrack(tracks, 2).morning)
        Assertions.assertEquals(returnList[3], conference.getTrack(tracks, 1).morning)
    }
    @Test
    fun `should be able to put the first event to the first lots`() {
        val tracks = mutableListOf<Track>()
        val conference = ConferenceManager()
        conference.addTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        talks.add(conference.transferStringToTalk(string1))
        talks.add(conference.transferStringToTalk(string2))
        conference.getAllSlotsByOrder(tracks)
        conference.arrangeOneTalk(talks, conference.getAllSlotsByOrder(tracks))
        Assertions.assertNotNull(conference.getTrack(tracks, 1).afternoon)
        Assertions.assertEquals(talks.size, 1)
    }
    @Test
    fun `should be able to put the first event to the first lots if successful return true`() {
        val tracks = mutableListOf<Track>()
        val conference = ConferenceManager()
        conference.addTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        talks.add(conference.transferStringToTalk(string1))
        talks.add(conference.transferStringToTalk(string2))
        conference.getAllSlotsByOrder(tracks)
        Assertions.assertEquals(conference.arrangeOneTalk(talks, conference.getAllSlotsByOrder(tracks)), true)
    }
    @Test
    fun `should be able to put the first event to the first lots if unsuccessful return false`() {
        val conference = ConferenceManager()
        val tracks = mutableListOf<Track>()
        conference.addTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 6000min"
        val talks = mutableListOf<Talk>()
        talks.add(conference.transferStringToTalk(string1))
        talks.add(conference.transferStringToTalk(string2))
        conference.getAllSlotsByOrder(tracks)
        Assertions.assertEquals(conference.arrangeOneTalk(talks, conference.getAllSlotsByOrder(tracks)), false)
    }
    @Test
    fun `if conferenceManager arrange should copy a input and origin input talks should be unmodified`() {
        val conference = ConferenceManager()
        val string1 = "Communicating Over Distance 60min"
        val talks1 = mutableListOf<Talk>()
        val talks2 = mutableListOf<Talk>()
        talks1.add(conference.transferStringToTalk(string1))
        talks2.add(conference.transferStringToTalk(string1))
        conference.arrangeConferenceWithNTracks(talks1, 1)
        Assertions.assertEquals(talks1, talks2)
    }
    @Test
    fun `if conferenceManager arrange the talks at current tracks successful it should return the list of Track as the result output`() {
        val conference = ConferenceManager()
        val string = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        repeat(50) { talks.add(conference.transferStringToTalk(string)) }
        Assertions.assertEquals(conference.arrangeConferenceWithNTracks(talks, 1).size, 8)
    }
}
