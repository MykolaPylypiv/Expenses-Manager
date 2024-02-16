package com.example.expensesmanager.ui.screens.start.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Operation
import com.example.expensesmanager.domain.model.TwoDragAnchors
import com.example.expensesmanager.ui.screens.start.StartViewModel

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GeneralInformation(
    viewModel: StartViewModel, currency: String, operations: List<Operation>, language: Language
) {
    val alpha = 0.7f
    val percent = viewModel.percentBudget(operations = operations)

    val dividerColor = viewModel.budgetColor(viewModel.percentBudget(operations = operations))

    val configuration = LocalConfiguration.current
    val width =
        if (percent != 0) {
            (configuration.screenWidthDp - 48 - 48 - (configuration.screenWidthDp / 100 * (100 - percent)))
        } else  (configuration.screenWidthDp - 48 - 48 - (configuration.screenWidthDp / 100))

    val density = LocalDensity.current

    val state = remember {
        AnchoredDraggableState(
            initialValue = TwoDragAnchors.Close,
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 200.dp.toPx() } },
            animationSpec = tween(),
        ).apply {
            updateAnchors(

                DraggableAnchors {
                    TwoDragAnchors.Close at 0f
                    TwoDragAnchors.Open at 100f
                })
        }
    }

    if (state.isAnimationRunning) {
        DisposableEffect(Unit) {
            onDispose {
                viewModel.expanded = when (state.currentValue) {
                    TwoDragAnchors.Open -> {
                        true
                    }

                    TwoDragAnchors.Close -> {
                        false
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .clip(RoundedCornerShape(viewModel.openCloseShape(viewModel.expanded)))
            .animateContentSize(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
            .height(viewModel.openCloseHeight(viewModel.expanded))
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    listOf(
                        MaterialTheme.colorScheme.secondary.copy(alpha),
                        MaterialTheme.colorScheme.background.copy(alpha)
                    )
                )
            )
            .anchoredDraggable(state, Orientation.Vertical), horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(36.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "${viewModel.budget(operations)} $currency",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )

        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Text(
                text = language.monthlyBudget,
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "${viewModel.percentBudget(operations = operations)}%",
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }

        Divider(
            color = dividerColor,
            thickness = 4.dp,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .clip(CircleShape)
                .width(width.dp)
        )

        Spacer(modifier = Modifier.height(viewModel.openCloseSpacer(viewModel.expanded, 24.dp)))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

            IconButton(onClick = { viewModel.expanded = !viewModel.expanded }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Detail information",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.alpha(viewModel.openCloseAlpha(viewModel.expanded))
                )
            }
        }

        Spacer(modifier = Modifier.height(viewModel.openCloseSpacer(viewModel.expanded, 20.dp)))

        Text(
            text = "${language.date}: ${viewModel.textDate(language = language)}",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "${language.beginning}: ${viewModel.incomes(operations)} $currency",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 24.dp)

        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "${language.costs}: ${viewModel.costs(operations)} $currency",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 24.dp)

        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "${language.avgCostsPerDay}: ${
                viewModel.avgCosts(
                    operations = operations, language = language
                )
            } $currency",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "${language.idealExpensesPerDay}: ${
                viewModel.idealCosts(
                    operations = operations, language = language
                )
            } $currency",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

            IconButton(onClick = { viewModel.expanded = !viewModel.expanded }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Detail information",
                    tint = MaterialTheme.colorScheme.tertiary,
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}