package com.example.expensesmanager.ui.screens.start.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensesmanager.navigation.NavigationTree

@Composable
fun SmallLayer(navController: NavController, screenRoute: String, title: String, padding: Dp) {
    val alpha = 0.7f

    Row(modifier = Modifier
        .padding(top = padding, bottom = 16.dp, start = 24.dp, end = 24.dp)
        .clip(RoundedCornerShape(40))
        .animateContentSize(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        .fillMaxWidth()
        .clickable {
            navController.navigate(screenRoute)
        }
        .background(
            Brush.horizontalGradient(
                listOf(
                    MaterialTheme.colorScheme.secondary.copy(alpha), MaterialTheme.colorScheme.background.copy(alpha)
                )
            )
        ), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.weight(1F))

        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}