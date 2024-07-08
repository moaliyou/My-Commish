package com.example.mycommish.feature.prize.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mycommish.feature.prize.data.local.datasource.PrizeEntity
import kotlinx.coroutines.flow.Flow

interface PrizeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPrize(prize: PrizeEntity)

    @Update
    suspend fun updatePrize(prize: PrizeEntity)

    @Delete
    suspend fun deletePrize(prize: PrizeEntity)

    @Query("SELECT * from prizes WHERE id = :id")
    fun getPrizeById(id: Int): Flow<PrizeEntity>

    @Query("SELECT * from prizes ORDER BY name ASC")
    fun getAllPrizes(): Flow<List<PrizeEntity>>
}