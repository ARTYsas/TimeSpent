package com.arty.timespent.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_stats")
data class UserStats(
    @PrimaryKey val id: Int = 0,
    val totalEffectiveMinutes: Long = 0,
    val totalSessions: Int = 0,
    val targetHours: Int = 1000 // Твоя цель, например, 1000 часов до релокации
)