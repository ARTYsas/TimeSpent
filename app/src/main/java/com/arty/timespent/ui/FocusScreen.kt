package com.arty.timespent.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arty.timespent.model.Intensity
import com.arty.timespent.viewmodel.TimerViewModel

@Composable
fun FocusScreen(viewModel: TimerViewModel) {
    val seconds by viewModel.seconds.collectAsState()
    val intensity by viewModel.currentIntensity.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("ФОКУС", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        // Таймер
        val mins = seconds / 60
        val secs = seconds % 60
        Text(
            text = String.format("%02d:%02d", mins, secs),
            fontSize = 80.sp,
            fontWeight = FontWeight.Black
        )

        // Переключатель интенсивности
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IntensityButton(
                text = "ФЛЕКСИМ",
                isSelected = intensity == Intensity.FLEX,
                onClick = { viewModel.setIntensity(Intensity.FLEX) }
            )
            IntensityButton(
                text = "БЕРСЕРК",
                isSelected = intensity == Intensity.BERSERK,
                onClick = { viewModel.setIntensity(Intensity.BERSERK) }
            )
        }

        // Кнопка СТОП
        Button(
            onClick = {
                viewModel.stopTimer()
                viewModel.saveSession() // Сначала сохраняем данные в базу
                viewModel.showSummary() // Потом переключаем экран
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8DE47C)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("СТОП", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun IntensityButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF8DE47C) else Color.LightGray
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text, color = Color.Black)
    }
}