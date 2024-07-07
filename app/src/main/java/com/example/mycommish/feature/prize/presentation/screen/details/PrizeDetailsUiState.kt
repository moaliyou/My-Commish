package com.example.mycommish.feature.prize.presentation.screen.details

import androidx.compose.runtime.Stable
import com.example.mycommish.feature.prize.domain.model.Prize
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class PrizeDetailsUiState(
    val prizeList: ImmutableList<Prize> = persistentListOf()
)
