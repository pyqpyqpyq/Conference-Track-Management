package com.example.conference.event

import com.example.conference.Track
import com.example.conference.constant.Constant
import com.example.conference.duration.Minutes

class Lunch : Event(name = "Lunch") {
    val duration = Minutes(60)
    companion object {
        fun arrangeLunch(tracks: MutableList<Track>): MutableList<Track> {
            tracks.forEach { track ->
                track.morning.arrangedEvents.add(Lunch())
                track.morning.addedTime.removeLast()
                track.morning.addedTime.add(Constant.LUNCH_TIME)
            }
            return tracks
        }
    }
}
