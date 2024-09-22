package com.example.mycommish.feature.prize.presentation.screen.details

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycommish.R
import com.example.mycommish.core.presentation.component.MyCommishAlertDialog
import com.example.mycommish.core.presentation.component.MyCommishFilterChip
import com.example.mycommish.core.presentation.component.MyCommishSearchField
import com.example.mycommish.core.presentation.component.MyCommishTopAppBar
import com.example.mycommish.core.presentation.component.NoDataIndicator
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.domain.util.SortTypes
import com.example.mycommish.feature.prize.presentation.component.PrizeCard
import kotlinx.collections.immutable.ImmutableList

@Composable
fun PrizeDetailsScreen(
    onActionClick: () -> Unit,
    navigateToEditPrize: (Long) -> Unit,
    prizeDetailsUiState: PrizeDetailsUiState,
    onDeletePrize: (Long) -> Unit,
    onSortClick: (SortTypes) -> Unit,
    onSearchValueChange: (String) -> Unit,
    onSearchClear: () -> Unit,
    searchText: String = "",
) {
    var deletePrizeConfirmed by rememberSaveable { mutableStateOf(false) }
    var prizeId by rememberSaveable { mutableLongStateOf(0) }

    Scaffold(
        topBar = {
            MyCommishTopAppBar(
                title = stringResource(R.string.prize_label),
                actionIcon = R.drawable.ic_add_prize,
                onActionClick = onActionClick
            )
        }
    ) { innerPadding ->
        PrizeDetailsBody(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .navigationBarsPadding(),
            prizeDetailsUiState = prizeDetailsUiState,
            contentPadding = PaddingValues(bottom = dimensionResource(R.dimen.content_extra_medium_padding)),
            onDeleteClick = {
                deletePrizeConfirmed = !deletePrizeConfirmed
                prizeId = it
            },
            onEditClick = { navigateToEditPrize(it) },
            onSortClick = onSortClick,
            onSearchValueChange = onSearchValueChange,
            onSearchClear = onSearchClear,
            searchText = searchText
        )

        if (deletePrizeConfirmed) {
            MyCommishAlertDialog(
                onDismissRequest = { deletePrizeConfirmed = !deletePrizeConfirmed },
                onConfirmation = {
                    onDeletePrize(prizeId)
                    deletePrizeConfirmed = !deletePrizeConfirmed
                },
                dialogTitle = "Deleting prize",
                dialogText = "Are you sure to delete this prize?",
                icon = Icons.Filled.Warning,
                confirmButtonText = stringResource(R.string.delete_label_button),
                dismissButtonText = stringResource(R.string.cancel_label_button)
            )
        }
    }
}

@Composable
private fun PrizeDetailsBody(
    modifier: Modifier = Modifier,
    prizeDetailsUiState: PrizeDetailsUiState,
    contentPadding: PaddingValues = PaddingValues(dimensionResource(R.dimen.small_padding)),
    onDeleteClick: (Long) -> Unit,
    onEditClick: (Long) -> Unit,
    onSortClick: (SortTypes) -> Unit,
    onSearchValueChange: (String) -> Unit,
    onSearchClear: () -> Unit,
    searchText: String = ""
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
        Column(
            modifier = modifier
        ) {
            MyCommishSearchField(
                inputValue = searchText,
                onInputValueChange = onSearchValueChange,
                placeholder = stringResource(R.string.search_prize_placeholder),
                trailingIcon = {
                    if (searchText.isNotEmpty() && searchText.isNotBlank()) {
                        IconButton(onClick = onSearchClear) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_clear),
                                contentDescription = "Clear icon",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(R.dimen.medium_padding))
            )
            MyCommishFilterChip(
                selectedSortOption = prizeDetailsUiState.selectedSortOption,
                onSortClick = onSortClick,
                sortingOptions = SortTypes.getSortOptions(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        dimensionResource(R.dimen.medium_padding)
                    )
            )
            PrizeList(
                prizes = prizes,
                contentPadding = contentPadding,
                onDeleteClick = onDeleteClick,
                onEditClick = onEditClick
            )
        }
    }
}

@Composable
private fun PrizeList(
    modifier: Modifier = Modifier,
    prizes: ImmutableList<Prize>,
    contentPadding: PaddingValues,
    onDeleteClick: (Long) -> Unit,
    onEditClick: (Long) -> Unit,
) {
    val lazyListState = rememberLazyListState()

    Column(
        modifier = modifier.padding(contentPadding)
    ) {
        LazyColumn(state = lazyListState) {
            items(items = prizes, key = { prize -> prize.name }) { prize ->
                PrizeCard(
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.medium_padding)),
                    prizeName = prize.name,
                    prizeValue = prize.value,
                    onDeleteClick = { onDeleteClick(prize.id) },
                    onEditClick = { onEditClick(prize.id) }
                )
            }
        }
    }

    SideEffect {
        lazyListState.requestScrollToItem(
            index = lazyListState.firstVisibleItemIndex,
            scrollOffset = lazyListState.firstVisibleItemScrollOffset
        )
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
    val viewModel: PrizeDetailsViewModel = hiltViewModel()
    val prizeDetailsUiState by viewModel.prizeDetailsUiState.collectAsState()

    MyCommishTheme {
        PrizeDetailsScreen(
            onActionClick = {},
            navigateToEditPrize = {},
            prizeDetailsUiState = prizeDetailsUiState,
            onDeletePrize = {},
            onSortClick = {},
            onSearchValueChange = {},
            onSearchClear = {},
        )
    }
}