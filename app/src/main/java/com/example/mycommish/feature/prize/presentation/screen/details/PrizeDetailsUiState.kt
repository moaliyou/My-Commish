package com.example.mycommish.feature.prize.presentation.screen.details

import com.example.mycommish.feature.prize.domain.model.Prize

data class PrizeDetailsUiState(
    val prizeList: List<Prize> = emptyList()
)
