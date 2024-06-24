@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mycommish.core.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycommish.R
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme

@Composable
fun MyCommishTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = TopAppBarDefaults.pinnedScrollBehavior(),
    title: String,
    actionIcon: Int = 0,
    onActionClick: () -> Unit = {},
    canNavigateBack: Boolean = false,
    navigationUpIcon: Int = 0,
    onNavigateUp: () -> Unit = {}
    ) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
        ),
        title = {
            Text(
                text = title,
                maxLines = 1,
                textAlign = if (canNavigateBack) TextAlign.Left else TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        actions = {
            if (!canNavigateBack) {
                IconButton(onClick = onActionClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(actionIcon),
                        contentDescription = title,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = ImageVector.vectorResource(navigationUpIcon),
                        contentDescription = title
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

@Preview
@Composable
private fun MyCommishTopAppBarPreview() {
    MyCommishTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.extra_medium_padding))
        ) {
            MyCommishTopAppBar(
                title = "Prize",
                actionIcon = R.drawable.track_earnings_active_icon,
                onActionClick = {}
            )
            MyCommishTopAppBar(
                title = "Prize Entry",
                canNavigateBack = true,
                navigationUpIcon = R.drawable.ic_navigation_back_arrow,
                onNavigateUp = {}
            )
        }
    }
}