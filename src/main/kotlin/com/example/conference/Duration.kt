package com.example.conference

import com.example.conference.constants.Constant

data class Duration(val base: Int, val unit: TimeUnit) {
    fun toInt(): Int {
        return base * unit.factor
    }
    override fun toString(): String {
        if (unit == TimeUnit.LIGHTNING) {
            return Constant.ABBREV_LIGHTNING
        }
        return base.toString() + Constant.ABBREV_MINUTES
    }
}
