package com.example.expensesmanager.ui.screens.start.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.expensesmanager.domain.model.DragAnchors
import com.example.expensesmanager.ui.composable.BarChart
import com.example.expensesmanager.ui.composable.Chart
import com.example.expensesmanager.ui.screens.start.StartViewModel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChartLayer(
    chartValues: List<Float>,
    viewModel: StartViewModel
) {
    val alpha = 0.8f

    val chartColors = listOf(
        listOf(
            Color(0xff2E3192).copy(alpha), Color(0xff1BFFFF).copy(alpha)
        ),
        listOf(
            Color(0xffD4145A).copy(alpha), Color(0xffFBB03B).copy(alpha)
        ),
        listOf(
            Color(0xff009245).copy(alpha), Color(0xffFCEE21).copy(alpha)
        ),
        listOf(
            Color(0xff662D8C).copy(alpha), Color(0xffED1E79).copy(alpha)
        ),
        listOf(
            Color(0xffEE9CA7).copy(alpha), Color(0xffFFDDE1).copy(alpha)
        ),
        listOf(
            Color(0xffFF512F).copy(alpha), Color(0xffDD2476).copy(alpha)
        ),
    )

    val density = LocalDensity.current

    var position by remember { mutableIntStateOf(0) }

    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Center,
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 200.dp.toPx() } },
            animationSpec = tween(),
        ).apply {
            updateAnchors(

                DraggableAnchors {
                    DragAnchors.Left at 600f
                    DragAnchors.Center at 0f
                    DragAnchors.Right at -600f
                })
        }
    }

    val scope = rememberCoroutineScope()

    if (state.isAnimationRunning) {
        DisposableEffect(Unit) {
            onDispose {
                position = when (state.currentValue) {
                    DragAnchors.Right -> {
                        viewModel.leftSwipe(position)
                    }

                    DragAnchors.Left -> {
                        viewModel.rightSwipe(position)
                    }

                    else -> {
                        return@onDispose
                    }
                }

                scope.launch {
                    state.animateTo(DragAnchors.Center)
                }
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .size(80.dp)
        .offset {
            IntOffset(
                x = state
                    .requireOffset()
                    .roundToInt(),
                y = 0,
            )
        }
        .anchoredDraggable(state, Orientation.Horizontal)) {

        when (position) {
            -1 -> Chart(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                colors = chartColors,
                inputValues = chartValues,
                withCenter = true
            )

            0 -> Chart(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                colors = chartColors,
                inputValues = chartValues,
                withCenter = false
            )

            1 -> BarChart(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                colors = chartColors,
                values = chartValues,
            )
        }
    }
}