package com.example.expensesmanager.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun BarChart(
    modifier: Modifier = Modifier,
    colors: List<List<Color>>,
    values: List<Float>,
    maxHeight: Dp = 300.dp
) {
    val borderColor = MaterialTheme.colorScheme.secondary
    val density = LocalDensity.current
    val strokeWidth = with(density) { 1.dp.toPx() }

    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .height(maxHeight)
                .drawBehind {
                    // draw X-Axis
                    drawLine(
                        color = borderColor,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = strokeWidth
                    )
                    // draw Y-Axis
                    drawLine(
                        color = borderColor,
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height),
                        strokeWidth = strokeWidth
                    )
                }),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        values.forEach { item ->
            Bar(
                value = item,
                brush = Brush.verticalGradient(colors[values.indexOf(item)]),
                maxHeight = maxHeight
            )
        }
    }

}

@Composable
private fun RowScope.Bar(
    value: Float, brush: Brush, maxHeight: Dp
) {
    val itemHeight = remember(value) { value * maxHeight.value / 100 }

    Spacer(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .height(itemHeight.dp)
            .width(28.dp)
            .background(brush)
    )

}