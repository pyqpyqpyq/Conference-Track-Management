package com.example.conference

import com.example.conference.constant.Constant.PRESS_ENTER
import java.util.Scanner

fun main() {
    val conferenceManager = ConferenceManager()
    println(PRESS_ENTER)
    val scan = Scanner(System.`in`)
    while (scan.hasNextLine()) {
        val inputSrc = scan.nextLine()
        conferenceManager.manageConference(inputSrc)
        println(PRESS_ENTER)
    }
}
