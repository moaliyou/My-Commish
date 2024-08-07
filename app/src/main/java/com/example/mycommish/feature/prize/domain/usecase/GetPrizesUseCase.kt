package com.example.mycommish.feature.prize.domain.usecase

import com.example.mycommish.feature.prize.data.mapper.toPrize
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.domain.repository.PrizeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPrizesUseCase(
    private val prizeRepository: PrizeRepo
) {
    operator fun invoke(): Flow<List<Prize>> = prizeRepository.getAllPrizes().map {
        it.map { prizeEntity -> prizeEntity.toPrize() }
    }
}