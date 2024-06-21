package com.example.mycommish.feature.onboarding.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.mycommish.feature.onboarding.data.local.PagesLocalDataSource.pages
import com.example.mycommish.feature.onboarding.presentation.component.OnBoardingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onGetStarted: (OnBoardingUiEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    2 -> "Get Started"
                    else -> ""
                }
            }
        }
        val scope = rememberCoroutineScope()

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(
                page = pages[index],
                onPageChange = {
                    scope.launch {
                        if (pagerState.currentPage == 2) {
                            onGetStarted(OnBoardingUiEvent.SaveAppEntry)
                        } else {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1
                            )
                        }
                    }
                },
                buttonState = buttonState,
                pageSize = pagerState.pageCount,
                selectedPagePosition = pagerState.currentPage
            )
        }
    }
}