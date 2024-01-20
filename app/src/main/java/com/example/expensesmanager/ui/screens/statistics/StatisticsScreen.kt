package com.example.expensesmanager.ui.screens.statistics

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.navigation.NavigationTree
import com.example.expensesmanager.ui.screens.statistics.components.CategoryItem
import com.example.expensesmanager.ui.screens.statistics.components.StatisticsLayer

@Composable
fun StatisticsScreen(
    navController: NavController, viewModel: StatisticsViewModel, language: Language
) {
    val operations by viewModel.operations.collectAsState(initial = listOf())
    val incomesCategories by viewModel.incomesCategories.collectAsState(initial = listOf())
    val costsCategories by viewModel.costsCategories.collectAsState(initial = listOf())
    val settings by viewModel.settings.collectAsState(initial = Settings())

    val alpha = 0.8f

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
            .animateContentSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {

                IconButton(
                    onClick = { navController.navigate(NavigationTree.Start.screenRoute) },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.weight(0.6f))

                Text(
                    text = viewModel.textDate(language = language),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 12.dp),
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        }

        item {
            StatisticsLayer(
                viewModel = viewModel,
                alpha = alpha,
                operations = operations,
                costsCategories = costsCategories,
                incomesCategories = incomesCategories,
                settings = settings,
                language = language
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = language.categories,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 24.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        items(
            items = viewModel.categoryOperationList(
                isCosts = viewModel.isCosts,
                language = language,
                alpha = alpha,
                costsCategories = costsCategories,
                incomesCategories = incomesCategories,
                operations = operations
            )
        ) { item ->
            CategoryItem(categoryOperation = item, settings = settings)
        }
    }
}