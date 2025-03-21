package com.llinsoft.finalchat.presentation.login_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import finalchat.composeapp.generated.resources.Res
import finalchat.composeapp.generated.resources.icon_gpt_reverse
import org.jetbrains.compose.resources.painterResource

@Composable
fun TopIconField(modifier: Modifier = Modifier) {
    val gradientColors = listOf(
        Color.White,
        Color.Transparent
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.radialGradient(gradientColors)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = painterResource(Res.drawable.icon_gpt_reverse),
                "",
            )
            Text("C H A T", color = Color.Red)
        }
    }
}