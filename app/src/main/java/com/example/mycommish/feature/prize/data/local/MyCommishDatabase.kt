package com.example.mycommish.feature.prize.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycommish.feature.prize.data.local.datasource.PrizeEntity
import com.example.mycommish.feature.prize.domain.repository.PrizeDao

@Database(
    entities = [PrizeEntity::class], version = 1
)
abstract class MyCommishDatabase() : RoomDatabase() {
    abstract fun prizeDao(): PrizeDao
}