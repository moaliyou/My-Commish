package com.example.mycommish.feature.prize.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycommish.R
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme

@Composable
fun PrizeCard(
    modifier: Modifier = Modifier,
    prizeName: String,
    prizeValue: String,
    onDeleteClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
) {
    var showPrizeMenu by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.extra_medium_padding)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = prizeName,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = prizeValue,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            )
            Spacer(modifier = Modifier.weight(0.1f))
            IconButton(onClick = { showPrizeMenu = !showPrizeMenu }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_settings),
                    contentDescription = stringResource(R.string.setting_icon),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            if (showPrizeMenu) {
                PrizeMenu(
                    modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer),
                    showPrizeMenu,
                    onDismissRequest = { showPrizeMenu = !showPrizeMenu },
                    onDeleteClick = onDeleteClick,
                    onEditClick = onEditClick,
                )
            }
        }
    }
}

@Composable
private fun PrizeMenu(
    modifier: Modifier = Modifier,
    showPrizeMenu: Boolean,
    onDismissRequest: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentSize(Alignment.BottomStart)
    ) {
        DropdownMenu(
            expanded = showPrizeMenu,
            onDismissRequest = onDismissRequest,
            modifier = modifier
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.edit_menu_label)) },
                onClick = {
                    onDismissRequest()
                    onEditClick()
                },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
                        contentDescription = stringResource(R.string.edit_menu_label)
                    )
                }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.delete_menu_label)) },
                onClick = {
                    onDismissRequest()
                    onDeleteClick()
                },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_delete),
                        contentDescription = stringResource(R.string.delete_menu_label)
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrizeCardPreview() {
    MyCommishTheme {
        Column {
            PrizeCard(
                prizeName = "Flex",
                prizeValue = "$1.00",
            )
        }
    }
}