package com.example.conference.utils

import com.example.conference.utils.ValidateUtil.Companion.validate
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ValidateUtilTest {
    @Test
    fun `should be able to return false if the input is not valid`() {
        val name = "Writing Fast Tests Against Enterprise Rails"
        val nameList = mutableListOf<String>()
        nameList.add(name)
        Assertions.assertEquals(validate(nameList), false)
    }
    @Test
    fun `should be able to return true if the input is valid`() {
        val name = "Writing Fast Tests Against Enterprise Rails 60min"
        val nameList = mutableListOf<String>()
        nameList.add(name)
        Assertions.assertEquals(validate(nameList), true)
    }
    @Test
    fun `should be able to return "Invalid input please check again" if the invalid input`() {
        val string = "Communicating Over Distance 60min invalid"
        val talks = mutableListOf<String>()
        talks.add(string)
        validate(talks)
    }
    @Test
    fun `should be classified to be in invalidate string with digits in the name`() {
        val string = "Communicating3 Over Distance 60min"
        val talks = mutableListOf<String>()
        talks.add(string)
        Assertions.assertEquals(validate(talks), false)
    }
    @Test
    fun `should be classified to be invalidate not end with time`() {
        val string = "Communicating Over Distance 60min day"
        val talks = mutableListOf<String>()
        talks.add(string)
        Assertions.assertEquals(validate(talks), false)
    }
    @Test
    fun `should be classified to be validate if both name without digits and end with time`() {
        val string = "Communicating Over Distance 60min"
        val talks = mutableListOf<String>()
        talks.add(string)
        Assertions.assertEquals(validate(talks), true)
    }
}
