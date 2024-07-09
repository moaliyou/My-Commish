package com.example.mycommish.feature.prize.domain.repository

import com.example.mycommish.feature.prize.data.local.PrizeEntity
import kotlinx.coroutines.flow.Flow

interface PrizeRepo {
    fun getAllPrizes(): Flow<List<PrizeEntity>>
    fun getSinglePrizeById(id: Int): Flow<PrizeEntity?>
    suspend fun insertPrize(prizeEntity: PrizeEntity)
    suspend fun updatePrize(prizeEntity: PrizeEntity)
    suspend fun deletePrize(prizeEntity: PrizeEntity)
}