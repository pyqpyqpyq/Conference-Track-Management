package com.example.conference.event

import com.example.conference.Track
import com.example.conference.constants.Constant

class NetworkEvent : Event(name = "Networking Event") {
    companion object {
        fun arrangeNetworkEvent(tracks: MutableList<Track>): MutableList<Track> {
            tracks.forEach { track ->
                track.afternoon.arrangedEvents.add(NetworkEvent())
                track.afternoon.addedTime.removeLast()
                track.afternoon.addedTime.add(Constant.NETWORK_EVENT_TIME)
            }
            return tracks
        }
    }
}
