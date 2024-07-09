package com.example.mycommish.feature.prize.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycommish.feature.prize.data.local.PrizeDao
import com.example.mycommish.feature.prize.data.local.PrizeEntity

@Database(
    entities = [PrizeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MyCommishDatabase : RoomDatabase() {
    abstract val prizeDao: PrizeDao
}