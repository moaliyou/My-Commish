package com.example.mycommish

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.mycommish.feature.onboarding.presentation.OnBoardingScreen
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme
import org.junit.Rule
import org.junit.Test

class OnBoardingScreenTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun scrollingForwardBetweenOnBoardingScreens() {
        // Start the app
        composeTestRule.setContent {
            MyCommishTheme {
                OnBoardingScreen()
            }
        }

        composeTestRule.onNodeWithText("Go").performClick()
        composeTestRule.onNodeWithText("Next").performClick()
    }

}