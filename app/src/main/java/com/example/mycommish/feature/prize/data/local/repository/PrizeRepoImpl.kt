package com.example.mycommish.feature.prize.data.local.repository

import com.example.mycommish.feature.prize.data.local.PrizeDao
import com.example.mycommish.feature.prize.data.local.PrizeEntity
import com.example.mycommish.feature.prize.domain.repository.PrizeRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PrizeRepoImpl @Inject constructor(
    private val prizeDao: PrizeDao
) : PrizeRepo {
    override fun getAllPrizes(): Flow<List<PrizeEntity>> = prizeDao.getAllPrizes()

    override fun getSinglePrizeById(id: Int) = prizeDao.getSinglePrizeById(id)

    override suspend fun insertPrize(prizeEntity: PrizeEntity) {
        prizeDao.insertPrize(prizeEntity)
    }

    override suspend fun updatePrize(prizeEntity: PrizeEntity) {
        prizeDao.updatePrize(prizeEntity)
    }

    override suspend fun deletePrize(prizeEntity: PrizeEntity) {
        prizeDao.deletePrize(prizeEntity)
    }
}