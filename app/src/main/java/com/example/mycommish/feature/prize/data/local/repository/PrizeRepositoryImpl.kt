package com.example.mycommish.feature.prize.data.local.repository

import com.example.mycommish.feature.prize.data.local.datasource.PrizeEntity
import com.example.mycommish.feature.prize.domain.repository.PrizeDao
import kotlinx.coroutines.flow.Flow

class PrizeRepositoryImpl(
    private val prizeDao: PrizeDao
) : PrizeDao {
    override suspend fun insertPrize(prize: PrizeEntity) {
        prizeDao.insertPrize(prize)
    }

    override suspend fun updatePrize(prize: PrizeEntity) {
        prizeDao.updatePrize(prize)
    }

    override suspend fun deletePrize(prize: PrizeEntity) {
        prizeDao.deletePrize(prize)
    }

    override fun getPrizeById(id: Int): Flow<PrizeEntity> = prizeDao.getPrizeById(id)

    override fun getAllPrizes(): Flow<List<PrizeEntity>> = prizeDao.getAllPrizes()
}