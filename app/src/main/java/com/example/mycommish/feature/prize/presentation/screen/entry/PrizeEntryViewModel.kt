package com.example.mycommish.feature.prize.presentation.screen.entry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.domain.usecase.PrizeUseCases
import com.example.mycommish.feature.prize.presentation.screen.PrizeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrizeEntryViewModel @Inject constructor(
    private val prizeUseCases: PrizeUseCases
) : ViewModel() {

    var prizeUiState by mutableStateOf(PrizeUiState())
        private set

    fun updateUiState(prize: Prize) {
        prizeUiState = PrizeUiState(
            prize = prize,
            isEntryValid = prizeUseCases.prizeEntryValidatorUseCase(prize)
        )
    }

}