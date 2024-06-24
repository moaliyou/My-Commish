@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mycommish.feature.prize.presentation.screen.details

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycommish.R
import com.example.mycommish.core.presentation.component.MyCommishTopAppBar
import com.example.mycommish.core.presentation.component.NoDataIndicator
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.presentation.component.PrizeCard

@Composable
fun PrizeDetailsScreen() {
    val prizeDetailsUiState = PrizeDetailsUiState()

    Scaffold(
        topBar = {
            MyCommishTopAppBar(
                title = stringResource(R.string.prize_label),
                actionIcon = R.drawable.ic_add_prize,
                onActionClick = {}
            )
        }
    ) { innerPadding ->
        PrizeDetailsBody(
            prizeDetailsUiState = prizeDetailsUiState,
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun PrizeDetailsBody(
    modifier: Modifier = Modifier,
    prizeDetailsUiState: PrizeDetailsUiState,
    contentPadding: PaddingValues = PaddingValues(dimensionResource(R.dimen.small_padding))
) {
    val prizes = prizeDetailsUiState.prizeList

    if (prizes.isEmpty()) {
        NoDataIndicator(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.medium_padding)),
            screenDataName = stringResource(R.string.prize_label),
            contentPadding = contentPadding
        )
    } else {
        PrizeList(
            prizes = prizes,
            contentPadding = contentPadding,
            modifier = modifier
        )
    }
}

@Composable
private fun PrizeList(
    modifier: Modifier = Modifier,
    prizes: List<Prize>,
    contentPadding: PaddingValues
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = prizes, key = { it.id }) { prize ->
            PrizeCard(
                prizeName = prize.name,
                prizeValue = prize.value,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.medium_padding))
            )
        }
    }
}


@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun PrizeDetailsScreenPreview() {
    MyCommishTheme {
        PrizeDetailsScreen()
    }
}