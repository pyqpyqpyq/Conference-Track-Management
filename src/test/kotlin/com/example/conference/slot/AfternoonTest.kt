package com.example.conference.slot

import com.example.conference.constant.Constant
import com.example.conference.constant.Constant.AFTERNOON_ALLOWABLE_DURATION
import com.example.conference.constant.Constant.AFTERNOON_DURATION
import com.example.conference.constant.Constant.AFTERNOON_START
import com.example.conference.duration.Lightning
import com.example.conference.duration.Minutes
import com.example.conference.event.Event
import com.example.conference.event.Talk
import com.example.conference.util.TransferUtil
import org.junit.jupiter.api.Test
import java.time.LocalTime
import kotlin.test.assertEquals

class AfternoonTest {
    @Test
    fun `The Afternoon should start from the 13pm`() {
        val afternoon = Afternoon()
        assertEquals(LocalTime.of(13, 0), afternoon.startTime)
    }

    @Test
    fun `The afternoon should be able 180min as the length ,and should use length as the init value of unassignedTimeLength `() {
        val afternoon = Afternoon()
        assertEquals(AFTERNOON_DURATION + AFTERNOON_ALLOWABLE_DURATION, afternoon.length)
        assertEquals(AFTERNOON_START, afternoon.startTime)
        assertEquals(AFTERNOON_DURATION + AFTERNOON_ALLOWABLE_DURATION, afternoon.unassignedTimeLength)
    }

    @Test
    fun `The Afternoon should use length as the init value `() {
        val afternoon = Afternoon()
        assertEquals(afternoon.unassignedTimeLength, afternoon.length)
    }

    @Test
    fun `The Slot should be able to set the length of unassignedTimeLength `() {
        val afternoon = Afternoon()
        afternoon.unassignedTimeLength = 0
        assertEquals(0, afternoon.unassignedTimeLength)
    }

    @Test
    fun `It should be able to set the arrangedEvents by order`() {
        val name = "Rails for Python Developers lightning"
        val duration = Lightning()
        val event = Talk(name, duration)
        val afternoon = Afternoon()
        val list = mutableListOf<Event>()
        list.add(event)
        afternoon.arrangedEvents = list
        assertEquals(event, afternoon.arrangedEvents[0])
    }

    @Test
    fun `It should be able to put the event to the slot if lots is longer than the event`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Minutes(duration))
        val afternoon = Afternoon()
        afternoon.put(event)
        assertEquals(event, afternoon.arrangedEvents[0])
    }

    @Test
    fun `It should be able to return true when put the event to the slot `() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Minutes(duration))
        val afternoon = Afternoon()
        assertEquals(true, afternoon.put(event))
    }

    @Test
    fun `It should be unable to put the event to the slot if lots is shorter than the event`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = Int.MAX_VALUE
        val event = Talk(name, Minutes(duration))
        val afternoon = Afternoon()
        afternoon.put(event)
        assertEquals(0, afternoon.arrangedEvents.size)
    }

    @Test
    fun `It should be able to return false when put the event to the slot unsuccessfully`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = Int.MAX_VALUE
        val event = Talk(name, Minutes(duration))
        val afternoon = Afternoon()
        assertEquals(false, afternoon.put(event))
    }

    @Test
    fun `The slot should be able to record the rest time and when put the event the restTime get minus`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val eventDuration = 60
        val event = Talk(name, Minutes(eventDuration))
        val afternoon = Afternoon()
        afternoon.put(event)
        assertEquals(Constant.AFTERNOON_ALLOWABLE_DURATION + AFTERNOON_DURATION - eventDuration, afternoon.unassignedTimeLength)
    }

    @Test
    fun `When there is a put operation to the slot ,the addedTime list should record the time of the arrange operation `() {
        val afternoon = Afternoon()
        val string1 = "Rails for Python Developers lightning"
        afternoon.put(TransferUtil.transferStringToTalk(string1))
        assertEquals(Constant.AFTERNOON_START.plusMinutes(Lightning().toMinutes().toLong()), afternoon.addedTime[1])
    }
}
