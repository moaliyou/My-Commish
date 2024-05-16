package com.example.mycommish.core.presentation.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycommish.core.presentation.navigation.Route
import com.example.mycommish.feature.onboarding.domain.use_case.AppEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MyCommishAppViewModel @Inject constructor(
    appEntryUseCase: AppEntryUseCase
): ViewModel() {
    private val _startDestination = mutableStateOf(Route.OnBoarding.route)
    val startDestination: State<String> = _startDestination

    init {
        appEntryUseCase.readAppEntry().onEach { canStartPrizeScreen ->
            if (canStartPrizeScreen) {
                _startDestination.value = Route.Home.route
            } else {
                _startDestination.value = Route.OnBoarding.route
            }
        }.launchIn(viewModelScope)
    }

}