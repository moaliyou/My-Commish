package com.example.mycommish.core.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.mycommish.R
import com.example.mycommish.core.presentation.component.CustomNavigationBar
import com.example.mycommish.core.presentation.navigation.Route.Companion.navigationItems
import com.example.mycommish.feature.onboarding.presentation.navigation.onBoardingScreen
import com.example.mycommish.feature.prize.presentation.navigation.navigateToPrizeEntry
import com.example.mycommish.feature.prize.presentation.navigation.prizeGraph

@Composable
fun MyCommishNavHost(
    modifier: Modifier = Modifier,
    startDestination: String,
    canShowNavigationBar: Boolean,
    onShowNavigationBar: () -> Unit,
    onHideNavigationBar: () -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (startDestination == Route.Home.route && canShowNavigationBar) {
                CustomNavigationBar(
                    navigationItems = navigationItems,
                    destination = currentDestination,
                    onSelectedNavigationItem = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.extra_medium_padding))
                )
            }
        },
    ) { innerPaddingValue ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            navigation(
                route = Route.AppStart.route,
                startDestination = Route.AppStart.OnBoarding.route
            ) {
                onBoardingScreen()
            }

            navigation(
                route = Route.Home.route,
                startDestination = Route.Home.Dashboard.route
            ) {
                composable(route = Route.Home.Dashboard.route) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPaddingValue),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Dashboard", style = MaterialTheme.typography.titleLarge)
                    }
                }

                prizeGraph(
                    onActionClick = {
                        navController.navigateToPrizeEntry()
                        onHideNavigationBar()
                    },
                    onNavigateUp = {
                        navController.navigateUp()
                        onShowNavigationBar()
                    }
                )

                composable(route = Route.Home.TrackEarnings.route) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(R.dimen.extra_medium_padding)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Track earnings", style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
        }
    }

}