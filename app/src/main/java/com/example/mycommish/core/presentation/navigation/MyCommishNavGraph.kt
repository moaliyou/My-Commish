package com.example.mycommish.core.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mycommish.R
import com.example.mycommish.feature.onboarding.presentation.navigation.onBoardingScreen

@Composable
fun MyCommishNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
         onBoardingScreen(
             onNavigateToHomeScreen = {
                 if (startDestination.equals(Route.AppStart)) {
                     navController.navigate(startDestination)
                 }
             }
         )

        composable(route = Route.Dashboard.route) {
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

        composable(route = Route.Prize.route) {
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

        composable(route = Route.TrackEarnings.route) {
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