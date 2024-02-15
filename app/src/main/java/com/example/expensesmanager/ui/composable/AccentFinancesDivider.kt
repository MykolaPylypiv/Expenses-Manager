package com.example.expensesmanager.ui.composable

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AccentFinanceDivider(isCosts: Boolean, alpha: Float, color: Color) {
    Divider(
        color = color.copy(alpha),
        thickness = 4.dp,
        modifier = if (isCosts) Modifier
            .animateContentSize()
            .clip(CircleShape)
            .fillMaxWidth()
            .padding(5.dp)
        else Modifier
            .animateContentSize()
            .clip(CircleShape)
            .width(0.dp)
    )
}