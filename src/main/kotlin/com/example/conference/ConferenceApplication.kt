package com.example.conference

import com.example.conference.constants.Constant.PRESS_ENTER
import java.util.Scanner

fun main() {
    val conferenceManager = ConferenceManager()
    println(PRESS_ENTER)
    val scan = Scanner(System.`in`)
    while (scan.hasNextLine()) {
        val inputSRC = scan.nextLine()
        try {
            conferenceManager.manageConference(inputSRC)
        } catch (e: Exception) {
            println("Unexpected Exception!")
        }
        println(PRESS_ENTER)
    }
}
