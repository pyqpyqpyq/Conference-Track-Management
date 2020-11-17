package com.example.project3.model

import com.example.project3.utils.Constant

class Conference {
    private var tracks = mutableListOf<Track>()
    fun addTrack() {
        tracks.add(Track(tracks.size + Constant.HUMAN_COMPUTER_DISTANCE))
    }
    fun getTrack(id: Int): Track {
        return tracks[id - Constant.HUMAN_COMPUTER_DISTANCE]
    }
}
