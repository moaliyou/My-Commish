package com.example.mycommish.feature.prize.domain.util

import com.example.mycommish.feature.prize.domain.model.Prize

sealed class PrizeValidationResult {
    data class Success(val prize: Prize) : PrizeValidationResult()
    data class Error(val errorMessage: String) : PrizeValidationResult()
}