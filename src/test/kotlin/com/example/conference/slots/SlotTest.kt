package com.example.conference.slots

import com.example.conference.ConferenceManager
import com.example.conference.durations.Lightning
import com.example.conference.durations.Minutes
import com.example.conference.events.Event
import com.example.conference.events.Talk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalTime
import kotlin.test.assertEquals

class SlotTest {
    @Test
    fun `It should be able to arrange the event to the slot`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Minutes(duration))
        val morning = Morning()
        morning.arrange(event)
    }
    @Test
    fun `It should be able to arrange the event to the slot if lots is longer than the event`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Minutes(duration))
        val morning = Morning()
        morning.arrange(event)
    }
    @Test
    fun `It should be able to assign the event to the slot `() {
        val name1 = "Writing Fast Tests Against Enterprise Rails"
        val duration1 = Minutes(60)
        val event1 = Talk(name1, duration1)
        val name2 = "Rails for Python Developers lightning"
        val duration2 = Lightning()
        val event2 = Talk(name2, duration2)
        val morning = Morning()
        val list = mutableListOf<Event>()
        morning.arrange(event1)
        list.add(event2)
        morning.events = list
        Assertions.assertEquals(morning.events[0], event2)
    }
    @Test
    fun `It should be able to return true when arrange the event to the slot `() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Minutes(duration))
        val morning = Morning()
        Assertions.assertEquals(morning.arrange(event), true)
    }
    @Test
    fun `It should be unable to arrange the event to the slot if lots is shorter than the event`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = Int.MAX_VALUE
        val event = Talk(name, Minutes(duration))
        val morning = Morning()
        morning.arrange(event)
        Assertions.assertEquals(morning.restLength, 180)
    }
    @Test
    fun `It should be able to return false when arrange the event to the slot unsuccessfully`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = Int.MAX_VALUE
        val event = Talk(name, Minutes(duration))
        val morning = Morning()
        Assertions.assertEquals(morning.arrange(event), false)
    }
    @Test
    fun `The morning should be able 180min as the length ,The Afternoon should be able 180min as the length,and should use length as the init value `() {
        val morning = Morning()
        val afternoon = Afternoon()
        Assertions.assertEquals(morning.length, 180)
        Assertions.assertEquals(afternoon.length, 240)
        Assertions.assertEquals(morning.restLength, 180)
        Assertions.assertEquals(afternoon.restLength, 240)
    }
    @Test
    fun `The morning and the Afternoon should use length as the init value `() {
        val morning = Morning()
        val afternoon = Afternoon()
        Assertions.assertEquals(morning.length, morning.restLength)
        Assertions.assertEquals(afternoon.length, afternoon.restLength)
    }
    @Test
    fun `The slot should be able to record the rest time and when arrange the event the restTime get minus`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Minutes(duration))
        val morning = Morning()
        morning.arrange(event)
        Assertions.assertEquals(morning.restLength, 120)
    }
    @Test
    fun `The slot can also get the corresponding arranged event by it id`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Minutes(duration))
        val morning = Morning()
        morning.arrange(event)
        Assertions.assertEquals(morning.getEvent(0), event)
    }
    @Test
    fun `The Morning should start from the 9am`() {
        val morning = Morning()
        Assertions.assertEquals(morning.startTime, LocalTime.of(9, 0))
    }
    @Test
    fun `The Afternoon should start from the 13pm`() {
        val afternoon = Afternoon()
        Assertions.assertEquals(afternoon.startTime, LocalTime.of(13, 0))
    }
    @Test
    fun `The added time should be add the start time first in the morning`() {
        val morning = Morning()
        Assertions.assertEquals(morning.addedTime[0], LocalTime.of(9, 0))
    }
    @Test
    fun `The added time should be add the start time first in the Afternoon`() {
        val afternoon = Afternoon()
        Assertions.assertEquals(afternoon.addedTime[0], LocalTime.of(13, 0))
    }
    @Test
    fun `When there is a arrange operation to the slot ,the addedTime list should record the time of the arrange operation `() {
        val conference = ConferenceManager()
        val morning = Morning()
        val string1 = "Rails for Python Developers lightning"
        morning.arrange(conference.transferStringToTalk(string1))
        Assertions.assertEquals(morning.addedTime[1], LocalTime.of(9, 5))
    }
}
