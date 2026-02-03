package com.arty.timespent.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(onStartClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Привет, User!", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text("Level B1", fontSize = 18.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(40.dp))

        Text("ETA: 15 июня 2027", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        Text("NY уже ждет тебя, поднажми!", fontSize = 14.sp, color = Color.DarkGray)

        Spacer(modifier = Modifier.height(60.dp))

        Button(
            onClick = onStartClick,
            modifier = Modifier.fillMaxWidth().height(64.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8DE47C)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("ВОРВАТЬСЯ", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.ExtraBold)
        }
    }
}