package com.example.expensesmanager.ui.screens.start

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.ui.screens.start.components.ChartLayer
import com.example.expensesmanager.ui.screens.start.components.GeneralInformation

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StartScreen(navController: NavController, viewModel: StartViewModel) {
    val color = Color(0xffEA8D8D)
    val color2 = Color(0xffA890FE)

    val alpha = 0.8f

    val chartValues = listOf(110f, 90f, 60f, 40f, 30f, 25f)

    val values = listOf(
        Category("Food", 25000, 60, Color(0xff1BFFFF).copy(alpha)),
        Category("Restaurant", 17289, 30, Color(0xffFBB03B).copy(alpha)),
        Category("Car", 2300, 9, Color(0xffFCEE21).copy(alpha)),
        Category("Woman", 500, 5, Color(0xffFFDDE1).copy(alpha)),
        Category("Utilities", 230, 2, Color(0xffED1E79).copy(alpha))
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        color.copy(0.3f), color2.copy(0.3f)
                    )
                )
            )
            .animateContentSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TopAppBar(
                title = {
                    Text(
                        text = "Expenses manager",
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 24.sp,
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
                actions = { IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.List, contentDescription = null)
                }},
            )
        }

        item {
            GeneralInformation(viewModel = viewModel)

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            ChartLayer(chartValues = chartValues, viewModel = viewModel)
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Categories",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 24.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Row {

            }
        }

        items(items = values) { item ->
            CategoryItem(item)
        }
    }
}

@Composable
fun CategoryItem(category: Category) {

    Column(
        horizontalAlignment = Alignment.Start, modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {

        Row {
            Text(
                text = category.name,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 18.sp,
                modifier = Modifier.padding(4.dp)
            )

            Text(
                text = "$ ${category.expense}",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 18.sp,
                modifier = Modifier.padding(4.dp)
            )

            Text(
                text = "(${category.percent}%)",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 18.sp,
                modifier = Modifier.padding(4.dp)
            )
        }

        Divider(
            color = category.color,
            thickness = 4.dp,
            modifier = Modifier
                .padding(end = 24.dp)
                .clip(CircleShape)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
