package com.example.mycommish.feature.prize.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycommish.feature.prize.domain.usecase.PrizeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrizeDetailsViewModel @Inject constructor(
    private val prizeUseCases: PrizeUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(PrizeDetailsUiState())
    val prizeDetailsUiState =
        prizeUseCases.getPrizes().map { PrizeDetailsUiState(it.toImmutableList()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = PrizeDetailsUiState()
            )

    fun deletePrize(id: Long) {
        viewModelScope.launch {
            prizeUseCases.deletePrize(prizeId = id)
        }
    }

    fun sortByHighestPrizeValue() {
        _uiState.update { currentState ->
            currentState.copy(
//                prizeList = prizeDetailsUiState.value.toString()
//                    .sortedByDescending { prize -> prize.value.toDouble() }
//                    .toImmutableList()
            )
        }
    }


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}