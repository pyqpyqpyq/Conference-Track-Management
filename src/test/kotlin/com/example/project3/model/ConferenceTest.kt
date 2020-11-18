package com.example.project3.model

import com.example.project3.model.slots.Afternoon
import com.example.project3.model.slots.Morning
import com.example.project3.utils.Constant
import com.example.project3.utils.Input
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ConferenceTest {
    @Test
    fun `should be able to init a conference and it can be able to add tracks`() {
        val conference = Conference()
        conference.addTrack()
        Assertions.assertEquals(conference.getTrack(1), Track(1))
    }
    @Test
    fun `should be able to rank the list of talks by their length in descending, so the shortest event should be in the last`() {
        val input = Input()
        val conference = Conference()
        val inputList = input.read()
        val string = "Rails for Python Developers lightning"
        conference.rankTalks(inputList)
        Assertions.assertEquals(inputList[inputList.size - Constant.HUMAN_COMPUTER_DISTANCE], input.transferStringToTalk(string))
    }
    @Test
    fun `should be able to rank the list of slots by their length in descending, so the shortest event should be in the last`() {
        val conference = Conference()
        val input = Input()
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
}
