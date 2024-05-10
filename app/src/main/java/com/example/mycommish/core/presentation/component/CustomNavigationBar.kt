package com.example.mycommish.core.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycommish.R
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme
import com.example.mycommish.core.presentation.navigation.Route

@Composable
fun CustomNavigationBar(
    modifier: Modifier = Modifier,
    navigationItems: List<Route>,
    destination: NavDestination?,
    onSelectedNavigationItem: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .clip(RoundedCornerShape(dimensionResource(R.dimen.navigation_medium_rounded)))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(dimensionResource(R.dimen.extra_medium_padding))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        navigationItems.forEach { item ->
            val selectedItem = destination?.hierarchy?.any { currentDestination ->
                currentDestination.route == item.route
            } == true

            CustomNavigationBarItem(
                navigationItem = item,
                isSelected = selectedItem,
                onItemClickChange = { onSelectedNavigationItem(item.route) }
            )

        }
    }
}

@Composable
private fun CustomNavigationBarItem(
    navigationItem: Route,
    isSelected: Boolean = false,
    onItemClickChange: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(dimensionResource(R.dimen.medium_padding)))
            .clickable(onClick = onItemClickChange)
            .padding(dimensionResource(R.dimen.medium_padding))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding)),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    if (isSelected) navigationItem.activeIcon
                    else navigationItem.inactiveIcon
                ),
                contentDescription = navigationItem.route,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview
@Composable
private fun CustomNavigationBarPreview() {
    MyCommishTheme {
        val navigationItems = Route.getNavigationItems()
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        CustomNavigationBar(
            navigationItems = navigationItems,
            destination = currentDestination,
            onSelectedNavigationItem = { navController.navigate(it) }
        )
    }
}