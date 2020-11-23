package com.example.conference.util

import com.example.conference.constant.Constant
import com.example.conference.exception.NameContainsDigitException
import com.example.conference.exception.WithoutInvalidTimeException
import java.util.regex.Pattern

object ValidateUtil {
    fun validate(inputString: MutableList<String>) {
        inputString.forEach {
            // check if the string end with time
            if (!Pattern.matches(Constant.RGX_END_WITH_TIME, it)) {
                throw WithoutInvalidTimeException()
            }
            // check if the string name contain digits
            val name = it.split(' ')
                .subList(0, it.split(' ').size - Constant.LAST_PART_REPRESENT_DURATION)
                .joinToString(" ")
            if (Pattern.matches(Constant.RGX_IF_CONTAIN_DIGIT, name)) {
                throw NameContainsDigitException()
            }
        }
    }
}
