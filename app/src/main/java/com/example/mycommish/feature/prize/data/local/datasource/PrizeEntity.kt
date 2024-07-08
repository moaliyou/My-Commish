package com.example.mycommish.feature.prize.data.local.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prizes")
data class PrizeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val value: Double,
    val description: String?
)
