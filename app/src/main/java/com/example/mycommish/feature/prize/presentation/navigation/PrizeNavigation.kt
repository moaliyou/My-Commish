package com.example.mycommish.feature.prize.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.mycommish.core.presentation.navigation.Route
import com.example.mycommish.feature.prize.presentation.screen.details.PrizeDetailsScreen
import com.example.mycommish.feature.prize.presentation.screen.entry.PrizeEntryScreen

fun NavController.navigateToPrizeEntry() = navigate(Route.Home.Prize.PrizeEntry.route)

fun NavGraphBuilder.prizeGraph(
    onActionClick: () -> Unit,
    onNavigateUp: () -> Unit,
    navigateToEditPrize: (Long) -> Unit
) {
    navigation(
        startDestination = Route.Home.Prize.PrizeDetails.route,
        route = Route.Home.Prize.route
    ) {
        composable(route = Route.Home.Prize.PrizeDetails.route) {
            PrizeDetailsScreen(
                onActionClick = onActionClick,
                navigateToEditPrize = { prizeId -> navigateToEditPrize(prizeId) }
            )
        }
        composable(route = Route.Home.Prize.PrizeEntry.route) {
            PrizeEntryScreen(onNavigateUp = onNavigateUp)
        }
    }
}