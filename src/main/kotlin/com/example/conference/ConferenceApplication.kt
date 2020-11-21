package com.example.conference

import com.example.conference.constants.Constant.Companion.PRESS_ENTER
import java.util.Scanner

fun main() {
    val conferenceManager = ConferenceManager()
    println(PRESS_ENTER)
    val scan = Scanner(System.`in`)
    while (scan.hasNextLine()) {
        scan.nextLine()
        conferenceManager.manageConference()
        println(PRESS_ENTER)
    }
}
