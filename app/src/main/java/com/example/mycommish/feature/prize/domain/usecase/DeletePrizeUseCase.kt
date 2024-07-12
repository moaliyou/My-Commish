package com.example.mycommish.feature.prize.domain.usecase

import com.example.mycommish.feature.prize.domain.repository.PrizeRepo

class DeletePrizeUseCase(
    private val prizeRepository: PrizeRepo
) {
    suspend operator fun invoke(prizeId: Long) {
        prizeRepository.deletePrizeById(prizeId)
    }
}