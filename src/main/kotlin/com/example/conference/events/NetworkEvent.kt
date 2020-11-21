package com.example.conference.events

import com.example.conference.Track
import java.time.LocalTime

class NetworkEvent : Event() {
    val name = "Networking Event"
    override fun toString(): String {
        return "$name"
    }
    companion object {
        fun arrangeNetworkEvent(tracks: MutableList<Track>): MutableList<Track> {
            tracks.forEach { track ->
                track.afternoon.events.add(NetworkEvent())
                track.afternoon.addedTime.removeLast()
                track.afternoon.addedTime.add(LocalTime.of(17, 0))
            }
            return tracks
        }
    }
}
