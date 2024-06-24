@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mycommish.feature.prize.presentation.screen.entry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycommish.R
import com.example.mycommish.core.presentation.component.MyCommishFilledTextField
import com.example.mycommish.core.presentation.component.MyCommishTopAppBar
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme
import com.example.mycommish.feature.prize.domain.model.Prize

@Composable
fun PrizeEntryScreen(
    onNavigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            MyCommishTopAppBar(
                title = stringResource(R.string.prize_entry_label),
                canNavigateBack = true,
                navigationUpIcon = R.drawable.ic_navigation_back_arrow,
                onNavigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        PrizeInputForm(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.extra_medium_padding)),
            prize = Prize(),
            contentPadding = innerPadding
        )
    }
}

@Composable
private fun PrizeInputForm(
    prize: Prize,
    modifier: Modifier = Modifier,
    onValueChange: (Prize) -> Unit = {},
    contentPadding: PaddingValues = PaddingValues()
) {
    Column(
        modifier = modifier.padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.extra_medium_padding))
    ) {
        MyCommishFilledTextField(
            modifier = Modifier
                .fillMaxWidth(),
            text = prize.name,
            onValueChange = { newPrizeName -> onValueChange(prize.copy(name = newPrizeName)) },
            label = { Text(text = stringResource(R.string.prize_name_label)) }
        )
        MyCommishFilledTextField(
            modifier = Modifier
                .fillMaxWidth(),
            text = prize.value,
            onValueChange = { newPrizeValue -> onValueChange(prize.copy(value = newPrizeValue)) },
            label = { Text(text = stringResource(R.string.prize_value_label)) }
        )
        MyCommishFilledTextField(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            text = prize.description,
            onValueChange = { newPrizeDescription ->
                onValueChange(prize.copy(description = newPrizeDescription))
            },
            label = {
                Text(text = stringResource(R.string.prize_description_label))
            },
            singleLine = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrizeEntryScreenPreview() {
    MyCommishTheme {
        PrizeEntryScreen(
            onNavigateUp = {}
        )
    }
}