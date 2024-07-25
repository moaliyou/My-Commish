package com.example.mycommish.feature.prize.domain.usecase

import com.example.mycommish.feature.prize.data.mapper.toPrizeEntity
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.domain.repository.PrizeRepo

class EditPrizeUseCase(
    private val prizeRepository: PrizeRepo
) {
    suspend operator fun invoke(prize: Prize) {
        prizeRepository.updatePrize(prize.toPrizeEntity())
    }
}