package com.example.expensesmanager.ui.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlin.math.min

@Composable
internal fun Chart(
    modifier: Modifier = Modifier,
    colors: List<Color>,
    inputValues: List<Float>,
    withCenter: Boolean,
) {
    val chartDegrees = 360f

    var startAngle = 270f

    val proportions = inputValues.map {
        it * 100 / inputValues.sum()
    }

    val angleProgress = proportions.map { prop ->
        chartDegrees * prop / 100
    }

    val progressSize = mutableListOf<Float>()

    LaunchedEffect(angleProgress) {
        progressSize.add(angleProgress.first())
        for (x in 1 until angleProgress.size) {
            progressSize.add(angleProgress[x] + progressSize[x - 1])
        }
    }

    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.Center) {

        val canvasSize = min(constraints.maxHeight, constraints.maxWidth)
        val size = Size(canvasSize.toFloat(), canvasSize.toFloat())
        val canvasSizeDp = with(LocalDensity.current) { canvasSize.toDp() }

        Canvas(modifier = Modifier.size(canvasSizeDp)) {

            angleProgress.forEachIndexed { index, angle ->
                val style = if (withCenter) Fill else Stroke(width = 20.dp.toPx())

                drawArc(
                    color = colors[index],
                    startAngle = startAngle,
                    sweepAngle = angle,
                    useCenter = withCenter,
                    size = size,
                    style = style

                )
                startAngle += angle
            }
        }

    }
}