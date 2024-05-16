package com.example.mycommish.core.presentation.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycommish.core.presentation.navigation.MyCommishNavHost

@Composable
fun MyCommishApp() {
    val myCommishAppViewModel = hiltViewModel<MyCommishAppViewModel>()
    val startDestination = myCommishAppViewModel.startDestination.value

    MyCommishNavHost(
        startDestination = startDestination
    )
}