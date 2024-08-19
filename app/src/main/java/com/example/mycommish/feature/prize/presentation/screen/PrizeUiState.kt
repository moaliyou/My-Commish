package com.example.mycommish.feature.prize.presentation.screen

import com.example.mycommish.feature.prize.domain.model.Prize

data class PrizeUiState(
    val prize: Prize = Prize(),
    val isEntryValid: Boolean = false,
    val validatorHasError: Boolean = false,
    val errorMessage: String = ""
)
