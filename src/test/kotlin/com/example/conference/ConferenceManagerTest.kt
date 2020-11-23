package com.example.conference

import com.example.conference.constant.Constant.CAN_NOT_BE_FOUND_PATH
import com.example.conference.constant.Constant.NAME_CONTAIN_DIGIT_FILE_PATH
import com.example.conference.constant.Constant.TIME_INVALID_FILE_PATH
import com.example.conference.constant.Constant.VALID_INPUT_PATH
import com.example.conference.duration.Minutes
import com.example.conference.event.Talk
import com.example.conference.slot.Afternoon
import com.example.conference.slot.Morning
import com.example.conference.util.TransferUtil.transferStringToTalk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ConferenceManagerTest {
    @Test
    fun `should be able to rank the list of slots by their length in descending, so the longest event should be in the first`() {
        val conferenceManager = ConferenceManager()
        val morning1 = Morning()
        val morning2 = Morning()
        val string = "Rails for Python Developers lightning"
        morning1.put(transferStringToTalk(string))
        val afternoon = Afternoon()
        val slotList = mutableListOf(morning1, morning2, afternoon)
        conferenceManager.rankSlots(slotList)
        assertEquals(afternoon, slotList[0])
        assertEquals(morning2, slotList[1])
        assertEquals(morning1, slotList[2])
    }
    @Test
    fun `should be able to get all the slots of the conference in Descending rate`() {
        val conferenceManager = ConferenceManager()
        val tracks = mutableListOf<Track>()
        conferenceManager.addOneTrack(tracks)
        conferenceManager.addOneTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        tracks[0].afternoon.put(transferStringToTalk(string1))
        tracks[1].morning.put(transferStringToTalk(string2))
        val returnList = conferenceManager.rankSlots(conferenceManager.getAllSlotsByOrder(tracks))
        assertEquals(tracks[1].afternoon, returnList[0])
        assertEquals(tracks[0].afternoon, returnList[1])
        assertEquals(tracks[0].morning, returnList[2])
        assertEquals(tracks[1].morning, returnList[3])
    }
    @Test
    fun `should be able to put the first event to the first lots`() {
        val conferenceManager = ConferenceManager()
        val tracks = mutableListOf<Track>()
        conferenceManager.addOneTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        talks.add(transferStringToTalk(string1))
        talks.add(transferStringToTalk(string2))
        conferenceManager.getAllSlotsByOrder(tracks)
        conferenceManager.arrangeOneTalk(talks, conferenceManager.getAllSlotsByOrder(tracks))
        assertNotNull(tracks[0].afternoon)
        assertEquals(1, talks.size)
    }
    @Test
    fun `should be able to put the first event to the first lots if successful return true`() {
        val conferenceManager = ConferenceManager()
        val tracks = mutableListOf<Track>()
        conferenceManager.addOneTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        talks.add(transferStringToTalk(string1))
        talks.add(transferStringToTalk(string2))
        conferenceManager.getAllSlotsByOrder(tracks)
        assertEquals(true, conferenceManager.arrangeOneTalk(talks, conferenceManager.getAllSlotsByOrder(tracks)))
    }
    @Test
    fun `should be able to put the first event to the first lots if unsuccessful return false`() {
        val conferenceManager = ConferenceManager()
        val tracks = mutableListOf<Track>()
        conferenceManager.addOneTrack(tracks)
        val string1 = "Rails for Python Developers lightning"
        val string2 = "Communicating Over Distance"
        val duration = Minutes(Int.MAX_VALUE)
        val talks = mutableListOf<Talk>()
        talks.add(transferStringToTalk(string1))
        talks.add(Talk(string2, duration))
        conferenceManager.getAllSlotsByOrder(tracks)
        assertEquals(false, conferenceManager.arrangeOneTalk(talks, conferenceManager.getAllSlotsByOrder(tracks)))
    }
    @Test
    fun `conferenceManager arrange should copy a input and origin input talks should be unmodified`() {
        val conference = ConferenceManager()
        val string1 = "Communicating Over Distance 60min"
        val talk1 = transferStringToTalk(string1)
        val talks1 = mutableListOf(talk1)
        conference.arrangeEvents(talks1, 1)
        assertEquals(1, talks1.size)
        assertEquals(talk1, talks1[0])
    }
    @Test
    fun `if conferenceManager arrange the talks at current tracks successful it should return the list of Track as the result output`() {
        val conference = ConferenceManager()
        val string = "Communicating Over Distance 60min"
        val talk = transferStringToTalk(string)
        val talks = mutableListOf(talk)
        val resultAfterArrange = conference.arrangeEvents(talks, 1)
        assertEquals(1, resultAfterArrange.size)
        assertEquals(2, resultAfterArrange[0].afternoon.arrangedEvents.size)
        assertEquals(talk, resultAfterArrange[0].afternoon.arrangedEvents[0])
        assertEquals("Networking Event", resultAfterArrange[0].afternoon.arrangedEvents[1].name)
    }
    @Test
    fun `should be able to output the result`() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        val conferenceManager = ConferenceManager()
        val conference = ConferenceManager()
        val string = "Communicating Over Distance 60min"
        val talks = mutableListOf<Talk>()
        repeat(5) { talks.add(transferStringToTalk(string)) }
        val result = conference.arrangeEvents(talks, 1)
        conferenceManager.printResult(result)
        val expectedResult = "Track 1:\n" +
            "09:00AM Communicating Over Distance 60min\n" +
            "10:00AM Communicating Over Distance 60min\n" +
            "12:00PM Lunch\n" +
            "01:00PM Communicating Over Distance 60min\n" +
            "02:00PM Communicating Over Distance 60min\n" +
            "03:00PM Communicating Over Distance 60min\n" +
            "05:00PM Networking Event\n"
        assertEquals(expectedResult, outContent.toString())
    }
    @Test
    fun `should print input source can not be found input if can not find file`() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        val conferenceManager = ConferenceManager()
        conferenceManager.manageConference(CAN_NOT_BE_FOUND_PATH)
        assertEquals("Can not find File, Please Check And Try Again!\n", outContent.toString())
    }
    @Test
    fun `should print invalid input if the input's time format is invalid`() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        val conferenceManager = ConferenceManager()
        conferenceManager.manageConference(TIME_INVALID_FILE_PATH)
        assertEquals("Invalid Input For Time Format, Please Check And Try Again!\n", outContent.toString())
    }
    @Test
    fun `should print invalid input if the input name contains digits`() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        val conferenceManager = ConferenceManager()
        conferenceManager.manageConference(NAME_CONTAIN_DIGIT_FILE_PATH)
        assertEquals("Invalid Input Contains Digits In Name, Please Check, And Try Again!\n", outContent.toString())
    }
    @Test
    fun `should print result if input is valid`() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        val conferenceManager = ConferenceManager()
        conferenceManager.manageConference(VALID_INPUT_PATH)
        assertEquals(
            "Track 1:\n" +
                "09:00AM Rails Magic 60min\n" +
                "10:00AM Common Ruby Errors 45min\n" +
                "10:45AM Lua for the Masses 30min\n" +
                "11:15AM Programming in the Boondocks of Seattle 30min\n" +
                "11:45AM Rails for Python Developers lightning\n" +
                "12:00PM Lunch\n" +
                "01:00PM Writing Fast Tests Against Enterprise Rails 60min\n" +
                "02:00PM Ruby on Rails: Why We Should Move On 60min\n" +
                "03:00PM Accounting-Driven Development 45min\n" +
                "03:45PM Woah 30min\n" +
                "04:15PM Ruby vs. Clojure for Back-End Development 30min\n" +
                "05:00PM Networking Event\n" +
                "Track 2:\n" +
                "09:00AM Ruby on Rails Legacy App Maintenance 60min\n" +
                "10:00AM Pair Programming vs Noise 45min\n" +
                "10:45AM Sit Down and Write 30min\n" +
                "11:15AM A World Without HackerNews 30min\n" +
                "12:00PM Lunch\n" +
                "01:00PM Communicating Over Distance 60min\n" +
                "02:00PM Overdoing it in Python 45min\n" +
                "02:45PM Ruby Errors from Mismatched Gem Versions 45min\n" +
                "03:30PM Clojure Ate Scala (on my project) 45min\n" +
                "04:15PM User Interface CSS in Rails Apps 30min\n" +
                "05:00PM Networking Event\n",
            outContent.toString()
        )
    }
}
