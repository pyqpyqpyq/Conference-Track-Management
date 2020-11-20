package com.example.conference.utils

import com.example.conference.utils.InputUtil.Companion.read
import org.junit.jupiter.api.Test

class InputUtilTest {
    @Test
    fun `should be able to read the file as input`() {
        read().forEach { println(it) }
    }
}
