package com.arty.timespent.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.arty.timespent.data.AppDatabase
import com.arty.timespent.data.UserStats
import com.arty.timespent.model.Intensity
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TimerViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).userStatsDao()

    private val _seconds = MutableStateFlow(0L)
    val seconds: StateFlow<Long> = _seconds.asStateFlow()

    private val _currentIntensity = MutableStateFlow(Intensity.FLEX)
    val currentIntensity: StateFlow<Intensity> = _currentIntensity.asStateFlow()

    private val _currentScreen = MutableStateFlow("main")
    val currentScreen: StateFlow<String> = _currentScreen.asStateFlow()

    private var timerJob: Job? = null

    fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _seconds.value += 1
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
    }

    fun resetTimer() {
        _seconds.value = 0
    }

    fun setIntensity(intensity: Intensity) {
        _currentIntensity.value = intensity
    }

    fun navigateTo(screen: String) {
        _currentScreen.value = screen
    }

    fun showSummary() {
        _currentScreen.value = "summary"
    }

    // Логика продукта: Флекс = x1, Берсерк = x2
    fun calculateEffectiveMinutes(): Long {
        val multiplier = if (_currentIntensity.value == Intensity.BERSERK) 2 else 1
        val minutes = _seconds.value / 60
        return if (minutes < 1 && _seconds.value > 0) 1L * multiplier else minutes * multiplier
    }

    fun saveSession() {
        val effectiveMins = calculateEffectiveMinutes()
        viewModelScope.launch {
            val currentStats = dao.getUserStats().first() ?: UserStats()
            val updatedStats = currentStats.copy(
                totalEffectiveMinutes = currentStats.totalEffectiveMinutes + effectiveMins,
                totalSessions = currentStats.totalSessions + 1
            )
            dao.insertOrUpdate(updatedStats)
        }
    }
}