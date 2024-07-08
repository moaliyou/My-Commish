package com.example.mycommish.feature.prize.domain.usecase

import com.example.mycommish.feature.prize.data.mapper.toPrize
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.domain.repository.PrizeDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPrizes(
    private val prizeDao: PrizeDao
) {
    operator fun invoke(): Flow<List<Prize>> = prizeDao.getAllPrizes().map {
        it.map { prizeEntity ->
            prizeEntity.toPrize()
        }
    }
}