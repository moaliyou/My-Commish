package com.example.mycommish.feature.prize.domain.usecase

import com.example.mycommish.feature.prize.data.mapper.toPrize
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.domain.repository.PrizeRepo
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first

class GetSinglePrizeUseCase(
    private val prizeRepository: PrizeRepo
) {
    suspend operator fun invoke(prizeId: Long): Prize =
        prizeRepository.getSinglePrizeById(prizeId)
            .filterNotNull()
            .first()
            .toPrize()
}