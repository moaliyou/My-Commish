package com.example.mycommish.core.presentation.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycommish.core.presentation.navigation.Route
import com.example.mycommish.feature.onboarding.domain.use_case.AppEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MyCommishAppViewModel @Inject constructor(
    appEntryUseCase: AppEntryUseCase
): ViewModel() {
    private val _startDestination: MutableState<String> = mutableStateOf(Route.AppStart.route)
    val startDestination: State<String> = _startDestination

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _canShowNavigationBar: MutableState<Boolean> = mutableStateOf(true)
    val canShowNavigationBar: State<Boolean> = _canShowNavigationBar

    init {
        appEntryUseCase.readAppEntry().onEach { canStartHomeApp ->
            if (canStartHomeApp) {
                _startDestination.value = Route.Home.route
            } else {
                _startDestination.value = Route.AppStart.route
            }
            delay(300L)
            _isLoading.value = false
        }.launchIn(viewModelScope)
    }

    fun showNavigationBar() {
        _canShowNavigationBar.value = true
    }

    fun hideNavigationBar() {
        _canShowNavigationBar.value = false
    }

}