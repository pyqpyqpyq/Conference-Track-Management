package com.example.conference.utils

import com.example.conference.Track
import java.time.format.DateTimeFormatter
import java.util.Locale

class OutputUtil {
    companion object {
        fun output(tracks: MutableList<Track>) {
            tracks.forEach { track ->
                println(track.toString())
                for (index in 0 until track.morning.events.size) {
                    print(track.morning.addedTime[index].format(DateTimeFormatter.ofPattern("hh:mma ", Locale.ENGLISH)))
                    println(track.morning.events[index].toString())
                }
                for (index in 0 until track.afternoon.events.size) {
                    print(track.afternoon.addedTime[index].format(DateTimeFormatter.ofPattern("hh:mma ", Locale.ENGLISH)))
                    println(track.afternoon.events[index].toString())
                }
            }
        }
    }
}
