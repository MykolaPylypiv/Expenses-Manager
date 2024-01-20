package com.example.expensesmanager.ui.screens.statistics.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.domain.model.Operation
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.ui.screens.AccentFinanceDivider
import com.example.expensesmanager.ui.screens.statistics.StatisticsViewModel

@Composable
fun StatisticsLayer(
    alpha: Float,
    viewModel: StatisticsViewModel,
    operations: List<Operation>,
    settings: Settings,
    language: Language,
    costsCategories: List<Category>,
    incomesCategories: List<Category>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 40.dp, end = 15.dp)
            .border(2.dp, Color(0xFF363837), RoundedCornerShape(10)),
        shape = RoundedCornerShape(10),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
    ) {
        Row(modifier = Modifier.height(150.dp)) {

            Column(modifier = Modifier
                .weight(1F)
                .padding(10.dp)
                .clip(RoundedCornerShape(15))
                .clickable { viewModel.isCosts = true }
                .animateContentSize()) {
                Text(
                    text = language.costs,
                    color = MaterialTheme.colorScheme.primary.copy(
                        viewModel.alpha(
                            viewModel.isCosts, 1F
                        )
                    ),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .weight(1F),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "${viewModel.costs(operations)}  ${settings.currency}",
                    color = Color.Red.copy(viewModel.alpha(viewModel.isCosts, alpha)),
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                        .fillMaxWidth(),
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )

                AccentFinanceDivider(isCosts = viewModel.isCosts, alpha = alpha, color = Color.Red)
            }

            Column(modifier = Modifier
                .weight(1F)
                .padding(10.dp)
                .clip(RoundedCornerShape(15))
                .clickable {
                    viewModel.isCosts = false
                }) {
                Text(
                    text = language.income,
                    color = MaterialTheme.colorScheme.primary.copy(
                        viewModel.alpha(
                            !viewModel.isCosts, 1F
                        )
                    ),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .weight(1F),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "${viewModel.incomes(operations)}  ${settings.currency}",
                    color = Color.Green.copy(viewModel.alpha(!viewModel.isCosts, alpha)),
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                        .fillMaxWidth(),
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )

                AccentFinanceDivider(
                    isCosts = !viewModel.isCosts, alpha = alpha, color = Color.Green
                )

            }
        }

        ChartLayer(
            chartValues = viewModel.chartValues(
                isCosts = viewModel.isCosts,
                costsCategories = costsCategories,
                incomesCategories = incomesCategories,
                operations = operations
            ), chartColors = viewModel.colors, language = language
        )
    }
}