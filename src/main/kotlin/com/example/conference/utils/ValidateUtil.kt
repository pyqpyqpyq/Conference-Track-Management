package com.example.conference.utils

import com.example.conference.constants.Constant
import java.util.regex.Pattern

class ValidateUtil {
    companion object {
        fun validate(inputString: MutableList<String>): Boolean {
            inputString.forEach {
                // check if the string end with time
                if (!Pattern.matches(Constant.RGX_END_WITH_TIME, it)) {
                    println(Constant.INVALID_INPUT)
                    return false
                }
                // check if the string name contain digits
                val name = it.split(' ')
                    .subList(0, it.split(' ').size - Constant.LAST_PART_REPRESENT_DURATION)
                    .joinToString(" ")
                if (Pattern.matches(Constant.RGX_IF_CONTAIN_DIGIT, name)) {
                    println(Constant.INVALID_INPUT)
                    return false
                }
            }
            return true
        }
    }
}
