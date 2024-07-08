package com.example.mycommish.feature.prize.domain.usecase

import com.example.mycommish.feature.prize.data.mapper.toPrizeEntity
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.domain.repository.PrizeDao

class AddPrizeUseCase(
    private val prizeDao: PrizeDao
) {
    suspend operator fun invoke(prize: Prize) {
        prizeDao.insertPrize(prize.toPrizeEntity())
    }
}