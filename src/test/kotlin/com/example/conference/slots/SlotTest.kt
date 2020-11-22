package com.example.conference.slots

import com.example.conference.constants.Constant
import com.example.conference.durations.Lightning
import com.example.conference.durations.Minutes
import com.example.conference.events.Event
import com.example.conference.events.Talk
import com.example.conference.utils.TransferUtil.Companion.transferStringToTalk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SlotTest {
    @Test
    fun `The Slot should be able to set the length of unassignedTimeLength `() {
        val morning = Morning()
        morning.unassignedTimeLength = 0
        val afternoon = Afternoon()
        afternoon.unassignedTimeLength = 0
        Assertions.assertEquals(0, morning.unassignedTimeLength)
        Assertions.assertEquals(0, afternoon.unassignedTimeLength)
    }

    @Test
    fun `It should be able to set the arrangedEvents by order`() {
        val name2 = "Rails for Python Developers lightning"
        val duration2 = Lightning()
        val event2 = Talk(name2, duration2)
        val morning = Morning()
        val list = mutableListOf<Event>()
        list.add(event2)
        morning.arrangedEvents = list
        Assertions.assertEquals(event2, morning.arrangedEvents[0])
    }

    @Test
    fun `It should be able to arrange the event to the slot if lots is longer than the event`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Minutes(duration))
        val morning = Morning()
        morning.arrange(event)
        Assertions.assertEquals(event, morning.arrangedEvents[0])
    }
    @Test
    fun `It should be able to return true when arrange the event to the slot `() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Minutes(duration))
        val morning = Morning()
        Assertions.assertEquals(true, morning.arrange(event))
    }
    @Test
    fun `It should be unable to arrange the event to the slot if lots is shorter than the event`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = Int.MAX_VALUE
        val event = Talk(name, Minutes(duration))
        val morning = Morning()
        morning.arrange(event)
        Assertions.assertEquals(0, morning.arrangedEvents.size)
    }
    @Test
    fun `It should be able to return false when arrange the event to the slot unsuccessfully`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = Int.MAX_VALUE
        val event = Talk(name, Minutes(duration))
        val morning = Morning()
        Assertions.assertEquals(false, morning.arrange(event))
    }
    @Test
    fun `The slot should be able to record the rest time and when arrange the event the restTime get minus`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val eventDuration = 60
        val event = Talk(name, Minutes(eventDuration))
        val morning = Morning()
        morning.arrange(event)
        Assertions.assertEquals(Constant.MORNING_DURATION - eventDuration, morning.unassignedTimeLength)
    }
    @Test
    fun `When there is a arrange operation to the slot ,the addedTime list should record the time of the arrange operation `() {
        val morning = Morning()
        val string1 = "Rails for Python Developers lightning"
        morning.arrange(transferStringToTalk(string1))
        Assertions.assertEquals(Constant.MORNING_START.plusMinutes(Lightning().toMinutes().toLong()), morning.addedTime[1])
    }
}
