package com.arty.timespent.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text("Текущий вайб:", fontSize = 18.sp)
        Text(intensity.label, fontSize = 32.sp, fontWeight = FontWeight.Bold)

        // Отображение времени (минуты:секунды)
        Text(
            text = String.format("%02d:%02d", seconds / 60, seconds % 60),
            fontSize = 80.sp,
            fontWeight = FontWeight.Thin
        )

        // Кнопки переключения вайба
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IntensityButton("Флексим", isSelected = intensity == Intensity.FLEX) {
                viewModel.setIntensity(Intensity.FLEX)
            }
            IntensityButton("Берсерк", isSelected = intensity == Intensity.BERSERK) {
                viewModel.setIntensity(Intensity.BERSERK)
            }
        }

        Button(
            onClick = {
                viewModel.stopTimer()
                viewModel.showSummary() // Показываем итоги
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
        onClick = onClick, // Она просто выполняет это действие
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF8DE47C) else Color.LightGray
        )
    ) {
        Text(text, color = Color.Black)
    }
}