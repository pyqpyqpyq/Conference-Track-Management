package com.example.conference.utils

import com.example.conference.durations.Lightning
import com.example.conference.durations.Minutes
import com.example.conference.events.Talk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class InputUtilTest {
    @Test
    fun `should be able to read the file as input`() {
        val input = InputUtil()
        input.read().forEach { println(it) }
    }
    @Test
    fun `should be able to transfer String to the Duration`() {
        val input = InputUtil()
        val string1 = "60min"
        val string2 = "lightning"
        Assertions.assertEquals(input.transferStringToDuration(string1), Minutes(60))
        Assertions.assertEquals(input.transferStringToDuration(string2), Lightning())
    }
    @Test
    fun `should be able to transfer legal String to the Talk`() {
        val input = InputUtil()
        val string = "Writing Fast Tests Against Enterprise Rails 60min"
        val name = "Writing Fast Tests Against Enterprise Rails"
        val timeUnit = Minutes(60)
        Assertions.assertEquals(input.transferStringToTalk(string), Talk(name, timeUnit))
    }
}
