package com.example.mycommish.feature.prize.presentation.screen.entry

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycommish.core.util.DecimalFormatter
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.domain.usecase.PrizeUseCases
import com.example.mycommish.feature.prize.domain.util.PrizeValidationResult
import com.example.mycommish.feature.prize.presentation.screen.PrizeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrizeEntryViewModel @Inject constructor(
    private val prizeUseCases: PrizeUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(PrizeUiState())
    val prizeUiState = _uiState.asStateFlow()
    private var isEntryValid by mutableStateOf(false)

    private val decimalFormatter = DecimalFormatter()

    fun updateUiState(prize: Prize) {
        val result = prizeUseCases.prizeEntryValidatorUseCase(prize)
        decimalFormatter.cleanup(prize.value)
        Log.d("RESULT", "result: $result\nisEntryValid: $isEntryValid")

        when (result) {
            is PrizeValidationResult.Success -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        prize = result.prize.copy(
                            value = decimalFormatter.cleanup(result.prize.value)
                        ),
                        isEntryValid = true,
                        validatorHasError = false
                    )
                }
            }

            is PrizeValidationResult.Error -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        prize = prize.copy(
                            value = decimalFormatter.cleanup(prize.value)
                        ),
                        isEntryValid = false,
                        validatorHasError = true,
                        errorMessage = result.errorMessage
                    )
                }
            }
        }
    }

    fun savePrize() {
        viewModelScope.launch(Dispatchers.IO) {
            prizeUseCases.addPrize(prizeUiState.value.prize)
        }
    }

}