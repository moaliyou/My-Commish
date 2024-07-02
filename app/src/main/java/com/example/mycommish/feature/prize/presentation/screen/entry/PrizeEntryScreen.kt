package com.example.mycommish.feature.prize.presentation.screen.entry

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycommish.R
import com.example.mycommish.core.presentation.component.MyCommishFilledTextField
import com.example.mycommish.core.presentation.component.MyCommishFloatingActionButton
import com.example.mycommish.core.presentation.component.MyCommishTopAppBar
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme
import com.example.mycommish.feature.prize.domain.model.Prize

@Composable
fun PrizeEntryScreen(
    onNavigateUp: () -> Unit,
    viewModel: PrizeEntryViewModel = hiltViewModel()
) {
    val prizeUiState = viewModel.prizeUiState
    Scaffold(
        topBar = {
            MyCommishTopAppBar(
                title = stringResource(R.string.prize_entry_label),
                canNavigateBack = true,
                navigationUpIcon = R.drawable.ic_navigation_back_arrow,
                onNavigateUp = onNavigateUp
            )
        },
        floatingActionButton = {
            if (prizeUiState.isEntryValid) {
                MyCommishFloatingActionButton(
                    onClick = {}
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { innerPadding ->
            PrizeInputForm(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.extra_medium_padding)),
                prize = prizeUiState.prize,
                onValueChange = viewModel::updateUiState,
                contentPadding = innerPadding
            )
            BackHandler(
                onBack = onNavigateUp
            )
        }
    )
}

@Composable
private fun PrizeInputForm(
    prize: Prize,
    modifier: Modifier = Modifier,
    onValueChange: (Prize) -> Unit = {},
    contentPadding: PaddingValues = PaddingValues()
) {
    val focusManager = LocalFocusManager.current

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
            label = { Text(text = stringResource(R.string.prize_name_label)) },
            keyboardOption = KeyboardOptions(imeAction = ImeAction.Next)
        )
        MyCommishFilledTextField(
            modifier = Modifier
                .fillMaxWidth(),
            text = prize.value,
            onValueChange = { newPrizeValue -> onValueChange(prize.copy(value = newPrizeValue)) },
            label = { Text(text = stringResource(R.string.prize_value_label)) },
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
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

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
private fun PrizeEntryScreenPreview() {
    MyCommishTheme {
        PrizeEntryScreen(
            onNavigateUp = {}
        )
    }
}