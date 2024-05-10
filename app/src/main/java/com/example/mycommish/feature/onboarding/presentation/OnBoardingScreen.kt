package com.example.mycommish.feature.onboarding.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.mycommish.R
import com.example.mycommish.feature.onboarding.data.local.PagesLocalDataSource.pages
import com.example.mycommish.feature.onboarding.presentation.component.OnBoardingPage
import com.example.mycommish.feature.onboarding.presentation.component.PrimaryContainerButton
import com.example.mycommish.feature.onboarding.presentation.component.SecondaryButton
import com.example.mycommish.core.presentation.ui.theme.primaryContainerBackground
import com.example.mycommish.core.presentation.ui.theme.primaryContainerContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onGetStarted: (OnBoardingUiEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryContainerBackground)
            .padding(dimensionResource(R.dimen.extra_medium_padding))
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Go")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }
        val scope = rememberCoroutineScope()

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(
                page = pages[index],
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .background(primaryContainerBackground)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(dimensionResource(R.dimen.medium_padding))
                .navigationBarsPadding(),
            horizontalArrangement = if (pagerState.currentPage == 0) {
                Arrangement.Center
            } else {
                Arrangement.SpaceBetween
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (buttonState.value[0].isNotEmpty()) {
                SecondaryButton(
                    text = buttonState.value[0],
                    contentColor = primaryContainerContent,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage.minus(1)
                            )
                        }
                    }
                )
            }

            PrimaryContainerButton(
                text = buttonState.value[1],
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage == 2) {
                            onGetStarted(OnBoardingUiEvent.SaveAppEntry)
                        } else {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage.plus(1)
                            )
                        }
                    }
                }
            )
        }
    }
}