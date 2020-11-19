package com.example.conference.utils

import org.junit.jupiter.api.Test

class InputUtilTest {
    @Test
    fun `should be able to read the file as input`() {
        val input = InputUtil()
        input.read().forEach { println(it) }
    }
}
