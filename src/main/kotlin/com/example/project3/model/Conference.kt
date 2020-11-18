package com.example.project3.model

import com.example.project3.model.events.Event
import com.example.project3.model.events.Talk
import com.example.project3.utils.Constant

class Conference {
    private var tracks = mutableListOf<Track>()
    fun addTrack() {
        tracks.add(Track(tracks.size + Constant.HUMAN_COMPUTER_DISTANCE))
    }
    fun getTrack(id: Int): Track {
        return tracks[id - Constant.HUMAN_COMPUTER_DISTANCE]
    }
    fun isLonger(event1: Event, event2: Event): Boolean {
        return event1.duration() > event2.duration()
    }
    fun rankTalks(inputList: MutableList<Talk>): MutableList<Talk> {
        inputList.sortByDescending { it.duration() }
        return inputList
    }
}
