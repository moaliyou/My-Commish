package com.example.mycommish.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mycommish.core.presentation.screen.MyCommishApp
import com.example.mycommish.core.presentation.screen.MyCommishAppViewModel
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val myCommishAppViewModel by viewModels<MyCommishAppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
            .setKeepOnScreenCondition {
                myCommishAppViewModel.isLoading.value
            }
        enableEdgeToEdge()

        setContent {
            MyCommishTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyCommishApp()
                }
            }
        }

    }
}