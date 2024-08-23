package com.example.mycommish.feature.prize.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycommish.feature.prize.domain.usecase.PrizeUseCases
import com.example.mycommish.feature.prize.domain.util.SortTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrizeDetailsViewModel @Inject constructor(
    private val prizeUseCases: PrizeUseCases
) : ViewModel() {

    private val _sortingTypeState = MutableStateFlow(SortTypes.ByName.option)
    val sortingTypeState = _sortingTypeState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val prizeDetailsUiState = sortingTypeState.flatMapLatest { sortingTypeStateValue ->
        prizeUseCases.getPrizes(sortingTypeStateValue)
            .map {
                PrizeDetailsUiState(
                    prizeList = it.toImmutableList(),
                    selectedSortOption = sortingTypeStateValue
                )
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = PrizeDetailsUiState()
    )

    fun deletePrize(id: Long) {
        viewModelScope.launch {
            prizeUseCases.deletePrize(prizeId = id)
        }
    }

    fun sortBy(sortingType: SortTypes) {
        _sortingTypeState.value = sortingType.option
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}