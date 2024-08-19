package com.example.mycommish.feature.prize.domain.usecase

import com.example.mycommish.core.util.AppConstants.EMPTY_FIELDS_ERROR
import com.example.mycommish.core.util.AppConstants.PRIZE_NAME_TOO_SHORT_ERROR
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.domain.util.PrizeValidationResult

class PrizeEntryValidatorUseCase {
    operator fun invoke(prizeEntryData: Prize) = with(prizeEntryData) {
        when {
            name.length < 3 && name.isNotEmpty() -> PrizeValidationResult.Error(PRIZE_NAME_TOO_SHORT_ERROR)
            name.isEmpty() || value.isEmpty() -> PrizeValidationResult.Error(EMPTY_FIELDS_ERROR)
            else -> PrizeValidationResult.Success(this)
        }
    }
}