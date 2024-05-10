package com.example.mycommish.feature.onboarding.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mycommish.feature.onboarding.presentation.OnBoardingScreen
import com.example.mycommish.feature.onboarding.presentation.OnBoardingViewModel

fun NavGraphBuilder.onBoardingScreen() {
    composable(route = "onboarding") {
        val onBoardingViewModel = hiltViewModel<OnBoardingViewModel>()

        OnBoardingScreen(
            onGetStarted = onBoardingViewModel::onEvent
        )
    }
}