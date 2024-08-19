package com.example.mycommish.feature.prize.presentation.screen.entry

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycommish.R
import com.example.mycommish.core.presentation.component.MyCommishFloatingActionButton
import com.example.mycommish.core.presentation.component.MyCommishTopAppBar
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme
import com.example.mycommish.feature.prize.presentation.component.PrizeInputForm

@Composable
fun PrizeEntryScreen(
    onNavigateUp: () -> Unit,
    viewModel: PrizeEntryViewModel = hiltViewModel()
) {
    val prizeUiState by viewModel.prizeUiState.collectAsState()

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
                    onClick = {
                        viewModel.savePrize()
                        onNavigateUp()
                    },
                    modifier = Modifier
                        .navigationBarsPadding()
                        .imePadding()
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { innerPadding ->
            PrizeInputForm(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .padding(dimensionResource(R.dimen.extra_medium_padding)),
                prize = prizeUiState.prize,
                onValueChange = viewModel::updateUiState,
                contentPadding = innerPadding,
                validatorHasErrors = prizeUiState.validatorHasError,
                errorMessage = prizeUiState.errorMessage
            )
            BackHandler(
                onBack = onNavigateUp
            )
        }
    )
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