package com.example.mycommish

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.mycommish.core.presentation.MainActivity
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme
import com.example.mycommish.feature.onboarding.presentation.OnBoardingScreen
import org.junit.Rule
import org.junit.Test

class OnBoardingScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun scroll_untilLastScreen() {
        // Start the app
        composeTestRule.activity.setContent {
            MyCommishTheme {
                OnBoardingScreen(
                    onGetStarted = {}
                )
            }
        }
        val nextButtonTestTag = composeTestRule.activity.getString(R.string.next_button_label)
        val pageThreeTitle =
            composeTestRule.activity.getString(R.string.page_three_title)

        composeTestRule.onNodeWithTag(nextButtonTestTag).performClick()

        composeTestRule.onNodeWithText(pageThreeTitle).assertExists()
    }

}