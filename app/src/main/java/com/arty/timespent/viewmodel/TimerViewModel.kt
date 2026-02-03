package com.arty.timespent.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arty.timespent.model.Intensity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class TimerViewModel : ViewModel() {
    // 1. Состояние счетчика времени
    private val _seconds = MutableStateFlow(0L)
    val seconds = _seconds.asStateFlow()

    // 2. Состояние текущего вайба (коэффициента)
    private val _currentIntensity = MutableStateFlow(Intensity.BASE)
    val currentIntensity = _currentIntensity.asStateFlow()

    // 3. Состояние навигации (показывать итоги или нет)
    private val _isSummaryVisible = MutableStateFlow(false)
    val isSummaryVisible = _isSummaryVisible.asStateFlow()

    private var timerJob: Job? = null

    private val _currentScreen = MutableStateFlow("main") // "main", "focus", "summary"
    val currentScreen = _currentScreen.asStateFlow()

    fun navigateTo(screen: String) {
        _currentScreen.value = screen
    }
    // Запуск таймера
    fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (isActive) {
                delay(1000)
                _seconds.value += 1
            }
        }
    }

    // Остановка таймера
    fun stopTimer() {
        timerJob?.cancel()
    }

    // Показать экран итогов
    fun showSummary() {
        _currentScreen.value = "summary" // Убедись, что тут строка "summary" совпадает с тем, что в MainActivity
    }

    // Вернуться к таймеру и сбросить время
    fun hideSummary() {
        _isSummaryVisible.value = false
        resetTimer()
    }

    fun setIntensity(intensity: Intensity) {
        _currentIntensity.value = intensity
    }

    fun resetTimer() {
        _seconds.value = 0
    }
}