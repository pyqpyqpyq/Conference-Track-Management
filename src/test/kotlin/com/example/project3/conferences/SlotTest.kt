package com.example.project3.conferences

import com.example.project3.model.conferences.Slot
import com.example.project3.model.events.Talk
import org.junit.jupiter.api.Test

class SlotTest {
    @Test
    fun `It should be able to arrange the event to the slot`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val duration = "60min"
        val event = Talk(name, duration)
        val slot = Slot()
        slot.arrange(event)
    }
}
