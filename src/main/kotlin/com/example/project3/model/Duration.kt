package com.example.project3.model

import com.example.project3.utils.Constant

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
