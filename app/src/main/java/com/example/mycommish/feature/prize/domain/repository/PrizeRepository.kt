package com.example.mycommish.feature.prize.domain.repository

import com.example.mycommish.feature.prize.data.local.datasource.Prize
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface PrizeRepository {
    fun getAllPrizes(): Flow<List<Prize>>
    suspend fun insertPrize(prize: Prize)
    suspend fun updatePrize(prize: Prize)
    suspend fun deletePrize(id: ObjectId)
}