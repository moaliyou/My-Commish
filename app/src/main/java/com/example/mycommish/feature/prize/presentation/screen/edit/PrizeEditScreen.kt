package com.example.mycommish.feature.prize.presentation.screen.edit

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycommish.R
import com.example.mycommish.core.presentation.component.MyCommishFloatingActionButton
import com.example.mycommish.core.presentation.component.MyCommishTopAppBar
import com.example.mycommish.feature.prize.presentation.component.PrizeInputForm
import com.example.mycommish.feature.prize.presentation.screen.entry.PrizeEntryViewModel

@Composable
fun PrizeEditScreen(
    onNavigateUp: () -> Unit,
    viewModel: PrizeEntryViewModel = hiltViewModel()
) {
    val prizeUiState = viewModel.prizeUiState

    Scaffold(
        topBar = {
            MyCommishTopAppBar(
                title = stringResource(R.string.prize_edit_label),
                canNavigateBack = true,
                navigationUpIcon = R.drawable.ic_navigation_back_arrow,
                onNavigateUp = onNavigateUp
            )
        },
        floatingActionButton = {
            if (prizeUiState.isEntryValid) {
                MyCommishFloatingActionButton(
                    onClick = {
                        //viewModel.savePrize()
                        onNavigateUp()
                    }
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