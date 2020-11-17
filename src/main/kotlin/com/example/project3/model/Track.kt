package com.example.project3.model

import com.example.project3.model.slots.Afternoon
import com.example.project3.model.slots.Morning

data class Track(var id: Int) {
    var morning = Morning()
    var afternoon = Afternoon()
}
