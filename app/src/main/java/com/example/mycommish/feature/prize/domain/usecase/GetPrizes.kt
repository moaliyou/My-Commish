package com.example.mycommish.feature.prize.domain.usecase

import com.example.mycommish.feature.prize.data.mapper.toPrize
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.domain.repository.PrizeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPrizes(
    private val prizeRepository: PrizeRepository
) {
    operator fun invoke(): Flow<List<Prize>> = prizeRepository.getAllPrizes().map {
        it.map { prizeObject -> prizeObject.toPrize() }
    }
}