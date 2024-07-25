package com.example.mycommish.feature.prize.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.mycommish.core.presentation.navigation.Route
import com.example.mycommish.feature.prize.presentation.screen.details.PrizeDetailsScreen
import com.example.mycommish.feature.prize.presentation.screen.details.PrizeDetailsViewModel
import com.example.mycommish.feature.prize.presentation.screen.edit.PrizeEditScreen
import com.example.mycommish.feature.prize.presentation.screen.entry.PrizeEntryScreen

fun NavController.navigateToPrizeEntry() = navigate(Route.Home.Prize.PrizeEntry.route)
fun NavController.navigateToPrizeEdit(prizeId: Long) =
    navigate("${Route.Home.Prize.PrizeEdit.route}/$prizeId")

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
            val viewModel: PrizeDetailsViewModel = hiltViewModel()
            val prizeDetailsUiState by viewModel.prizeDetailsUiState.collectAsState()

            PrizeDetailsScreen(
                onActionClick = onActionClick,
                navigateToEditPrize = { prizeId -> navigateToEditPrize(prizeId) },
                prizeDetailsUiState = prizeDetailsUiState,
                onDeletePrize = { prizeId -> viewModel.deletePrize(prizeId) }
            )
        }
        composable(route = Route.Home.Prize.PrizeEntry.route) {
            PrizeEntryScreen(onNavigateUp = onNavigateUp)
        }
        composable(
            route = Route.Home.Prize.PrizeEdit.routeWithArgument,
            arguments = listOf(navArgument(Route.Home.Prize.PrizeEdit.PRIZE_ID) {
                type = NavType.LongType
            })
        ) {
            PrizeEditScreen(onNavigateUp = onNavigateUp)
        }
    }
}