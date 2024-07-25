package com.example.mycommish.feature.prize.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PrizeDao {
    @Query("SELECT * FROM prizes ORDER BY name ASC")
    fun getAllPrizes(): Flow<List<PrizeEntity>>

    @Query("SELECT * FROM prizes WHERE id = :id")
    fun getSinglePrizeById(id: Long): Flow<PrizeEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPrize(prizeEntity: PrizeEntity)

    @Update
    suspend fun updatePrize(prizeEntity: PrizeEntity)

    @Delete
    suspend fun deletePrize(prizeEntity: PrizeEntity)

    @Query("DELETE FROM prizes WHERE id = :id")
    suspend fun deletePrizeById(id: Long)
}