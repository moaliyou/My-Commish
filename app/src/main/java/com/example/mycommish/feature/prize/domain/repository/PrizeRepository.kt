package com.example.mycommish.feature.prize.domain.repository

import com.example.mycommish.feature.prize.data.local.datasource.PrizeObject
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface PrizeRepository {
    fun getAllPrizes(): Flow<List<PrizeObject>>
    suspend fun insertPrize(prizeObject: PrizeObject)
    suspend fun updatePrize(prizeObject: PrizeObject)
    suspend fun deletePrize(id: ObjectId)
}