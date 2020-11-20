package com.example.conference.utils

import com.example.conference.Track

class OutputUtil {
    fun output(tracks: MutableList<Track>) {
        tracks.forEach { track ->
            println(track.toString())
            for (index in 0 until track.morning.events.size) {
                print(track.morning.addedTime[index])
                println(track.morning.events[index].toString())
            }
            for (index in 0 until track.afternoon.events.size) {
                print(track.afternoon.addedTime[index])
                println(track.afternoon.events[index].toString())
            }
        }
    }
}
