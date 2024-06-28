package com.example.mycommish.core.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycommish.core.presentation.navigation.MyCommishNavHost

@Composable
fun MyCommishApp() {
    val myCommishAppViewModel = hiltViewModel<MyCommishAppViewModel>()
    val startDestination by myCommishAppViewModel.startDestination

    MyCommishNavHost(
        startDestination = startDestination,
        onShowNavigationBar = myCommishAppViewModel::showNavigationBar,
        onHideNavigationBar = myCommishAppViewModel::hideNavigationBar,
        canShowNavigationBar = myCommishAppViewModel.canShowNavigationBar.value
    )
}