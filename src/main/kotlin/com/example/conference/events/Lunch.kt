package com.example.conference.events

import com.example.conference.Track
import com.example.conference.constants.Constant
import com.example.conference.durations.Minutes

class Lunch : Event() {
    val name = "Lunch"
    val duration = Minutes(60)
    override fun toString(): String {
        return name
    }
    companion object {
        fun arrangeLunch(tracks: MutableList<Track>): MutableList<Track> {
            tracks.forEach { track ->
                track.morning.events.add(Lunch())
                track.morning.addedTime.removeLast()
                track.morning.addedTime.add(Constant.LUNCH_TIME)
            }
            return tracks
        }
    }
}
