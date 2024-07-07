package com.example.mycommish.feature.prize.domain.usecase

import com.example.mycommish.feature.prize.data.mapper.toPrizeObject
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.domain.repository.PrizeRepository

class AddPrizeUseCase(
    private val prizeRepository: PrizeRepository
) {
    suspend operator fun invoke(prize: Prize) {
        prizeRepository.insertPrize(prize.toPrizeObject())
    }
}