@file:OptIn(ExperimentalFoundationApi::class)

package com.example.mycommish.feature.onboarding.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycommish.R
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme
import com.example.mycommish.feature.onboarding.data.local.PagesLocalDataSource.pages
import com.example.mycommish.feature.onboarding.domain.model.Page

@Composable
fun OnBoardingPage(
    page: Page,
    onPageChange: () -> Unit,
    buttonState: State<String>,
    pageSize: Int,
    selectedPagePosition: Int
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(dimensionResource(R.dimen.extra_medium_padding)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = ImageVector.vectorResource(page.image),
                contentDescription = stringResource(page.title),
                modifier = Modifier
                    .size(dimensionResource(R.dimen.default_image_size)),
                contentScale = ContentScale.Fit
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter)
                .clip(
                    RoundedCornerShape(
                        topStart = dimensionResource(R.dimen.onboarding_corner_size),
                        topEnd = dimensionResource(R.dimen.onboarding_corner_size)
                    )
                )
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.large_padding)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding))
            ) {
                PageIndicator(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(top = dimensionResource(R.dimen.medium_padding)),
                    pageSize = pageSize,
                    selectedPagePosition = selectedPagePosition,
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                ContentSection(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    title = page.title,
                    subtitle = page.subtitle
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.2f))
                ActionButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    buttonState = buttonState,
                    onPageChange = onPageChange
                )
            }
        }
    }
}

@Composable
private fun ContentSection(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @StringRes subtitle: Int
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding))
    ) {
        Text(
            text = stringResource(title),
            textAlign = TextAlign.Left,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = stringResource(subtitle),
            textAlign = TextAlign.Left,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun ActionButton(
    modifier: Modifier = Modifier,
    buttonState: State<String>,
    onPageChange: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (buttonState.value.isNotEmpty()) {
            PrimaryButton(
                text = buttonState.value,
                onClick = onPageChange,
            )
        } else {
            PrimaryIconButton(
                onClick = onPageChange,
                icon = ImageVector.vectorResource(R.drawable.ic_foward_arrow),
                contentDescription = "next",
                modifier = Modifier
                    .size(dimensionResource(R.dimen.icon_button_size))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnBoardingPagePreview() {
    val page = pages[2]
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

    MyCommishTheme {
        OnBoardingPage(
            page = page,
            onPageChange = { },
            buttonState = buttonState,
            pageSize = pagerState.pageCount,
            selectedPagePosition = pagerState.currentPage
        )
    }
}