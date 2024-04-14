package com.example.mycommish.feature.onboarding.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycommish.R
import com.example.mycommish.feature.onboarding.domain.model.Page
import com.example.mycommish.ui.theme.MyCommishTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = painterResource(page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(text = stringResource(page.title))
        Text(text = stringResource(page.subtitle))

    }
}

@Preview(showBackground = true)
@Composable
private fun OnBoardingPagePreview() {
    val page = Page(
        title = R.string.page_one_title,
        subtitle = R.string.page_one_subtitle,
        image = R.drawable.ic_launcher_foreground
    )
    MyCommishTheme {
        OnBoardingPage(
            modifier = Modifier.fillMaxSize(),
            page = page
        )
    }
}