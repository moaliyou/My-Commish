package com.example.mycommish.feature.onboarding.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycommish.R
import com.example.mycommish.feature.onboarding.data.local.PagesLocalDataSource.pages
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
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.default_image_size)),
            painter = painterResource(page.image),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            text = stringResource(page.title),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primaryContainer
        )
        Text(
            text = stringResource(page.subtitle),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primaryContainer
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun OnBoardingPagePreview() {
    val page = pages[0]
    MyCommishTheme {
        OnBoardingPage(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimaryContainer),
            page = page
        )
    }
}