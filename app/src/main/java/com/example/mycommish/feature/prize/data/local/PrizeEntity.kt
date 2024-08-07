package com.example.mycommish.feature.prize.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "prizes",
    indices = [Index(
        value = ["name", "value"],
        unique = true
    )]
)
data class PrizeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var value: Double,
    var description: String?
)