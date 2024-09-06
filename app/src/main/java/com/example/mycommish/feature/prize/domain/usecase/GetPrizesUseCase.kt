package com.example.mycommish.feature.prize.domain.usecase

import com.example.mycommish.feature.prize.data.mapper.toPrize
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.domain.repository.PrizeRepo
import com.example.mycommish.feature.prize.domain.util.SortTypes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPrizesUseCase(
    private val prizeRepository: PrizeRepo
) {
    operator fun invoke(sortingType: String): Flow<List<Prize>> = when (sortingType) {
        SortTypes.HighestValue.option -> prizeRepository.getAllPrizes().map {
            it.map { prizeEntity -> prizeEntity.toPrize() }
                .sortedByDescending { prize -> prize.value }
        }

        SortTypes.LowestValue.option -> prizeRepository.getAllPrizes().map {
            it.map { prizeEntity -> prizeEntity.toPrize() }
                .sortedBy { prize -> prize.value }
        }

        else -> prizeRepository.getAllPrizes().map {
            it.map { prizeEntity -> prizeEntity.toPrize() }.sortedBy { prize -> prize.name }
        }
    }
}