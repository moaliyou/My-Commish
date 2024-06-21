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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.mycommish.R
import com.example.mycommish.core.presentation.component.CustomNavigationBar
import com.example.mycommish.core.presentation.navigation.Route.Companion.navigationItems
import com.example.mycommish.feature.onboarding.presentation.navigation.onBoardingScreen

@Composable
fun MyCommishNavHost(
    modifier: Modifier = Modifier,
    startDestination: String
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (startDestination == Route.Home.route) {
                CustomNavigationBar(
                    navigationItems = navigationItems,
                    destination = currentDestination,
                    onSelectedNavigationItem = { route -> navController.navigate(route) },
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.extra_medium_padding))
                )
            }
        },
    ) { innerPaddingValue ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier.padding(innerPaddingValue)
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
                            .padding(dimensionResource(R.dimen.extra_medium_padding)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Dashboard", style = MaterialTheme.typography.titleLarge)
                    }
                }

                composable(route = Route.Home.Prize.route) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(R.dimen.extra_medium_padding)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Prize", style = MaterialTheme.typography.titleLarge)
                    }
                }

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