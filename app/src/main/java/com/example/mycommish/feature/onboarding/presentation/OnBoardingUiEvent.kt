package com.example.mycommish.feature.onboarding.presentation

sealed class OnBoardingUiEvent {
    data object SaveAppEntry: OnBoardingUiEvent()
}