package com.example.mycommish.feature.prize.presentation.screen.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycommish.core.presentation.navigation.Route.Home.Prize.PrizeEdit.PRIZE_ID
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.domain.usecase.PrizeUseCases
import com.example.mycommish.feature.prize.presentation.screen.PrizeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrizeEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val prizeUseCases: PrizeUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(PrizeUiState())
    val prizeUiState = _uiState.asStateFlow()

    private val prizeId: Long = checkNotNull(savedStateHandle[PRIZE_ID])

    init {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    prize = prizeUseCases.getPrize(prizeId),
                    isEntryValid = true
                )
            }
        }
    }

    fun updateUiState(prize: Prize) {
        _uiState.update { currentState ->
            currentState.copy(
                prize = prize,
                isEntryValid = prizeUseCases.prizeEntryValidatorUseCase(prize)
            )
        }
    }

    fun updatePrize() {
        viewModelScope.launch(Dispatchers.IO) {
            prizeUseCases.editPrize(prizeUiState.value.prize)
        }
    }
}