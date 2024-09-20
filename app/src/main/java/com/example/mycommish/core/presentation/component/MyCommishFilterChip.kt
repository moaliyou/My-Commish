package com.example.mycommish.core.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import com.example.mycommish.R
import com.example.mycommish.feature.prize.domain.util.SortTypes
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyCommishFilterChip(
    modifier: Modifier = Modifier,
    selectedSortOption: String,
    onSortClick: (SortTypes) -> Unit,
    sortingOptions: ImmutableList<SortTypes>,
    shape: Shape = FilterChipDefaults.shape,
    iconColor: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.extra_medium_padding))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_filter),
                contentDescription = "Sort Icon",
                tint = iconColor
            )
            Text(
                text = "Sort by",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
            verticalArrangement = Arrangement.Center,
            maxItemsInEachRow = 4
        ) {
            sortingOptions.forEach { option ->
                val selected = selectedSortOption == option.option
                FilterChip(
                    selected = selected,
                    onClick = {
                        onSortClick(option)
                    },
                    shape = shape,
                    label = { Text(text = option.option) },
                    leadingIcon = if (selected) {
                        {
                            Icon(
                                Icons.Outlined.Clear,
                                contentDescription = "Clear Icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    }
                )
            }
        }
    }
}