package com.example.expensesmanager.ui.screens.statistics

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.navigation.NavigationTree
import com.example.expensesmanager.ui.screens.statistics.components.CategoryItem
import com.example.expensesmanager.ui.screens.statistics.components.ChartLayer

@Composable
fun StatisticsScreen(
    navController: NavController, viewModel: StatisticsViewModel, language: Language
) {
    val alpha = 0.8f

    val values = listOf(
        Category("Food", 25000, 60, Color(0xff498563).copy(alpha)),
        Category("Restaurant", 17289, 30, Color(0xfff0f0aa).copy(alpha)),
        Category("Car", 2300, 9, Color(0xfffaad45).copy(alpha)),
        Category("Woman", 500, 5, Color(0xffb44927).copy(alpha)),
        Category("Utilities", 230, 2, Color(0xff932701).copy(alpha))
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF363837))
            .animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {

                IconButton(
                    onClick = { navController.navigate(NavigationTree.Start.screenRoute) },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Back")
                }

                Spacer(modifier = Modifier.weight(0.6f))

                Text(
                    text = viewModel.textDate,
                    color = Color.White,
                    modifier = Modifier.padding(top = 12.dp),
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        }

        item {
            StatisticsLayer(alpha)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Categories",
                color = Color.White,
                fontSize = 24.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        items(items = values) { item ->
            CategoryItem(item)
        }
    }
}

@Composable
fun StatisticsLayer(alpha: Float) {
    val chartValues = listOf(110f, 90f, 60f, 40f, 30f)

    var isCosts by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 40.dp, end = 15.dp),
        shape = RoundedCornerShape(10),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha)
        )
    ) {
        Row(modifier = Modifier.height(150.dp)) {

            val alpha = 0.8f

            Column(modifier = Modifier
                .weight(1F)
                .padding(10.dp)
                .clip(RoundedCornerShape(15))
                .clickable {
                    isCosts = true
                }
                .animateContentSize()) {
                Text(
                    text = "Costs",
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .weight(1F),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "$ -200",
                    color = Color.Red.copy(if (isCosts) alpha else 0.4f),
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                        .fillMaxWidth(),
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )

                Divider(
                    color = Color.Red.copy(alpha),
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

            Column(modifier = Modifier
                .weight(1F)
                .padding(10.dp)
                .clip(RoundedCornerShape(15))
                .clickable {
                    isCosts = false
                }) {
                Text(
                    text = "Income",
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .weight(1F),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "$ 1946",
                    color = Color.Green.copy(if (!isCosts) alpha else 0.4f),
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                        .fillMaxWidth(),
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )

                Divider(
                    color = Color.Green.copy(alpha),
                    thickness = 4.dp,
                    modifier = if (!isCosts) Modifier
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
        }

        ChartLayer(chartValues = chartValues)
    }
}