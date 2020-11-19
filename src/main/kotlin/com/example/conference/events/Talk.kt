package com.example.conference.events

import com.example.conference.durations.Duration

data class Talk(var name: String, var duration: Duration) : Event()
