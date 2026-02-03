package com.arty.timespent.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arty.timespent.model.Intensity

@Composable
fun SummaryScreen(
    seconds: Long,
    intensity: Intensity,
    onClose: () -> Unit
) {
    // Считаем эффективные минуты: время * коэффициент
    val effectiveMinutes = ((seconds / 60.0) * intensity.multiplier).toInt()

    // Считаем, сколько "часов будущего" мы выкупили (для примера возьмем 1 к 1)
    val hoursSaved = String.format("%.1f", effectiveMinutes / 60.0)

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Кнопка закрытия (крестик)
        IconButton(onClick = onClose, modifier = Modifier.align(Alignment.Start)) {
            Text("✕", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        Text("Итоги сессии:", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F51B5))

        Spacer(modifier = Modifier.height(32.dp))

        Text("Ты прозанимался ${seconds / 60} мин,", fontSize = 18.sp)
        Text("но в режиме \"${intensity.label}\" это", fontSize = 18.sp)

        Text("$effectiveMinutes", fontSize = 64.sp, fontWeight = FontWeight.Black, color = Color(0xFF8DE47C))
        Text("эффективных минут!", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(48.dp))

        Text("Дата релокации", fontSize = 22.sp)
        Text("приблизилась на:", fontSize = 22.sp)
        Text("$hoursSaved часа!", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold)
    }
}