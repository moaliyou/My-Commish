package com.example.mycommish.core.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme
import com.example.mycommish.feature.onboarding.domain.use_case.AppEntryUseCase
import com.example.mycommish.feature.onboarding.presentation.OnBoardingScreen
import com.example.mycommish.feature.onboarding.presentation.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appEntryUseCase: AppEntryUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            appEntryUseCase.readAppEntry().collect{
                Log.d("appEntry", it.toString())
            }
        }

        setContent {
            MyCommishTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val onBoardingViewModel = hiltViewModel<OnBoardingViewModel>()
                    OnBoardingScreen(onGetStarted = onBoardingViewModel::onEvent)
                }
            }
        }
    }
}