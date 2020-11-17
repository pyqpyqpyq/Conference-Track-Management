package com.example.project3.utils

import org.junit.jupiter.api.Test

class InputTest {
    @Test
    fun `should be able to read the file as input`() {
        val input = Input()
        input.read().forEach { println(it) }
    }
}
