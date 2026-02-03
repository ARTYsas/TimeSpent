package com.arty.timespent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState // ОБЯЗАТЕЛЬНО
import androidx.compose.runtime.getValue       // ОБЯЗАТЕЛЬНО
import androidx.compose.ui.Modifier
import com.arty.timespent.ui.FocusScreen
import com.arty.timespent.ui.MainScreen
import com.arty.timespent.ui.SummaryScreen
import com.arty.timespent.viewmodel.TimerViewModel

class MainActivity : ComponentActivity() {

    private val timerViewModel: TimerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                // Мы подписываемся на изменение экрана во ViewModel
                val screen by timerViewModel.currentScreen.collectAsState()
                val seconds by timerViewModel.seconds.collectAsState()
                val intensity by timerViewModel.currentIntensity.collectAsState()

                Surface(modifier = Modifier.fillMaxSize()) {
                    // Если screen меняется на "summary", Compose сам перерисует экран
                    when (screen) {
                        "main" -> MainScreen(onStartClick = {
                            timerViewModel.startTimer()
                            timerViewModel.navigateTo("focus")
                        })
                        "focus" -> FocusScreen(viewModel = timerViewModel)
                        "summary" -> SummaryScreen(
                            seconds = seconds,
                            intensity = intensity,
                            onClose = {
                                timerViewModel.navigateTo("main")
                                timerViewModel.resetTimer()
                            }
                        )
                    }
                }
            }
        }
    }
}