package com.example.conference.events

import com.example.conference.Track
import com.example.conference.constants.Constant

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
                track.afternoon.addedTime.add(Constant.NETWORK_EVENT_TIME)
            }
            return tracks
        }
    }
}
