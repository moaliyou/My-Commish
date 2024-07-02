package com.example.mycommish.feature.prize.domain.usecase

import com.example.mycommish.feature.prize.domain.model.Prize

class PrizeEntryValidatorUseCase {
    operator fun invoke(prizeEntryData: Prize) = with(prizeEntryData) {
        name.isNotEmpty() && value.isNotEmpty()
    }
}