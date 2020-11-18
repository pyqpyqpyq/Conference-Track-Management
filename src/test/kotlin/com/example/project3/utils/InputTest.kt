package com.example.project3.utils

import com.example.project3.model.Duration
import com.example.project3.model.TimeUnit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class InputTest {
    @Test
    fun `should be able to read the file as input`() {
        val input = Input()
        input.read().forEach { println(it) }
    }
    @Test
    fun `should be able to transfer String to the Duration`() {
        val input = Input()
        val string1 = "60min"
        val string2 = "lightning"
        Assertions.assertEquals(input.transferStringToDuration(string1), Duration(60, TimeUnit.MINUTES))
        Assertions.assertEquals(input.transferStringToDuration(string2), Duration(1, TimeUnit.LIGHTNING))
    }
}
