package com.example.mycommish.feature.prize.domain.util

import kotlinx.collections.immutable.persistentListOf

sealed class SortTypes(val option: String = "") {
    data object HighestValue : SortTypes(option = "Highest value")
    data object LowestValue : SortTypes(option = "Lowest value")
    data object ByName : SortTypes(option = "By name")

    companion object {
        fun getSortOptions() = persistentListOf(
            HighestValue, LowestValue
        )
    }
}