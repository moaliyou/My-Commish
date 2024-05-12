package com.example.mycommish.core.presentation.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycommish.core.presentation.navigation.MyCommishNavHost

@Composable
fun MyCommishApp(navController: NavHostController = rememberNavController()) {
    val myCommishAppViewModel = hiltViewModel<MyCommishAppViewModel>()
    val startDestination = myCommishAppViewModel.startDestination.value

    MyCommishNavHost(
        navController = navController,
        startDestination = startDestination
    )
}