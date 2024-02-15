package com.example.expensesmanager.ui.screens.start

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.navigation.NavigationTree
import com.example.expensesmanager.ui.screens.start.components.GeneralInformation
import com.example.expensesmanager.ui.screens.start.components.OperationItem
import com.example.expensesmanager.ui.screens.start.components.SmallLayer

@Composable
fun StartScreen(navController: NavController, viewModel: StartViewModel, language: Language) {
    val operations by viewModel.operations.collectAsState(initial = listOf())
    val settings by viewModel.settings.collectAsState(initial = Settings())

    val color = Color(0xffEA8D8D)
    val color2 = Color(0xffA890FE)

    val alpha = 0.8F

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        color.copy(0.3f), color2.copy(0.3f)
                    )
                )
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            GeneralInformation(
                viewModel = viewModel,
                currency = settings.currency,
                operations = operations,
                language = language
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            SmallLayer(
                navController = navController,
                screenRoute = NavigationTree.Statistics.screenRoute,
                title = language.statistics,
                padding = 24.dp
            )
        }

        item {
            SmallLayer(
                navController = navController,
                screenRoute = NavigationTree.Add.screenRoute,
                title = language.add,
                padding = 16.dp
            )
        }

        item {
            SmallLayer(
                navController = navController,
                screenRoute = NavigationTree.Account.screenRoute,
                title = language.account,
                padding = 16.dp
            )
        }

        item {
            SmallLayer(
                navController = navController,
                screenRoute = NavigationTree.Settings.screenRoute,
                title = language.settings,
                padding = 16.dp
            )
        }

        item {
            Column(
                modifier = Modifier
                    .padding(top = 110.dp)
                    .clip(RoundedCornerShape(6))
                    .animateContentSize(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                MaterialTheme.colorScheme.secondary.copy(alpha),
                                MaterialTheme.colorScheme.background.copy(alpha)
                            )
                        )
                    )
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    text = language.operations,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                operations.reversed().map { operation ->
                    OperationItem(
                        icon = Icons.Filled.ShoppingCart,
                        name = operation.name,
                        category = operation.category,
                        income = operation.sum,
                        currency = settings.currency,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}