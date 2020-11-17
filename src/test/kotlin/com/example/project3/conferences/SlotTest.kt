package com.example.project3.conferences

import com.example.project3.model.conferences.Afternoon
import com.example.project3.model.conferences.Morning
import com.example.project3.model.conferences.Slot
import com.example.project3.model.events.Talk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SlotTest {
    @Test
    fun `It should be able to arrange the event to the slot`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, duration)
        val morning=Morning()
        morning.arrange(event)
    }
    @Test
    fun `The morning should be able 180min as the length ,The Afternoon should be able 180min as the length,and should use length as the init value `() {
        val morning=Morning()
        val afternoon=Afternoon()
        Assertions.assertEquals(morning.length,180)
        Assertions.assertEquals(afternoon.length,240)
        Assertions.assertEquals(morning.restTime,180)
        Assertions.assertEquals(afternoon.restTime,240)
    }
    @Test
    fun `The morning and the Afternoon should use length as the init value `() {
        val morning=Morning()
        val afternoon=Afternoon()
        Assertions.assertEquals(morning.length,morning.restTime)
        Assertions.assertEquals(afternoon.length,afternoon.restTime)
    }
    @Test
    fun `The slot should be able to record the rest time and when arrange the event the restTime get minus`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = 60
        val event = Talk(name, duration)
        val morning=Morning()
        morning.arrange(event)
        Assertions.assertEquals(morning.restTime,120)
    }
}
