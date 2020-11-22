package com.example.conference.utils

import com.example.conference.exceptions.NameContainsDigitException
import com.example.conference.exceptions.WithoutInvalidTimeException
import com.example.conference.utils.ValidateUtil.validate
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class ValidateUtilTest {
    @Test
    fun `should be able to return true if the input is valid`() {
        val talk = "Writing Fast Tests Against Enterprise Rails 60min"
        val talksList = mutableListOf<String>()
        talksList.add(talk)
        assertDoesNotThrow { validate(talksList) }
    }
    @Test
    fun `should be classified to be in invalidate string with digits in the name`() {
        val string = "Communicating3 Over Distance 60min"
        val talks = mutableListOf<String>()
        talks.add(string)
        assertFailsWith<NameContainsDigitException> { validate(talks) }
    }
    @Test
    fun `should be classified to be invalidate not end with time`() {
        val string = "Communicating Over Distance 60min day"
        val talks = mutableListOf<String>()
        talks.add(string)
        assertFailsWith<WithoutInvalidTimeException> { validate(talks) }
    }
}
