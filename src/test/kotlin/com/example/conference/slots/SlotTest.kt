package com.example.conference.slots

import com.example.conference.Duration
import com.example.conference.TimeUnit
import com.example.conference.events.Talk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SlotTest {
    @Test
    fun `It should be able to arrange the event to the slot`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Duration(duration, TimeUnit.MINUTES))
        val morning = Morning()
        morning.arrange(event)
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
        val event = Talk(name, Duration(duration, TimeUnit.MINUTES))
        val morning = Morning()
        morning.arrange(event)
        Assertions.assertEquals(morning.restLength, 120)
    }
    @Test
    fun `The slot can also get the corresponding arranged event by it id`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, Duration(duration, TimeUnit.MINUTES))
        val morning = Morning()
        morning.arrange(event)
        Assertions.assertEquals(morning.getEvent(0), event)
    }
}
