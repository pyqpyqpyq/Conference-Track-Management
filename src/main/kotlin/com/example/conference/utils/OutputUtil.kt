package com.example.conference.utils

import com.example.conference.Track

class OutputUtil {
    fun output(tracks: MutableList<Track>) {
        tracks.forEach { track -> println(track.toString()) }
    }
}
