package com.example.expensesmanager.ui.screens.incomes

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
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
import com.example.expensesmanager.navigation.NavigationTree
import com.example.expensesmanager.ui.screens.incomes.components.CategoryIconItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomesScreen(
    navController: NavController, viewModel: IncomesViewModel
) {
    var isCosts by remember { mutableStateOf(true) }
    val alpha = 0.8f

    var money by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Add incomes or costs", color = Color.White, fontSize = 24.sp)
        }, navigationIcon = {
            IconButton(onClick = { navController.navigate(NavigationTree.Start.screenRoute) }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Back")
            }
        })
    }) { padding ->

        Column(modifier = Modifier.padding(padding)) {
            TextField(value = money,
                onValueChange = {
                    money = it
                },
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                label = { Text(text = "UAH")},
                colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.background)
            )

            TextField(value = details,
                onValueChange = {
                    details = it
                },
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                label = { Text(text = "Details")},
                colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.background)
            )

            TextField(value = comment,
                onValueChange = {
                    comment = it
                },
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                label = { Text(text = "Comment (not necessarily)")},
                colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.background)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp),
                shape = RoundedCornerShape(10),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background.copy(alpha)
                )
            ) {
                Row {

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
                                .fillMaxWidth(),
                            fontSize = 18.sp,
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
                                .fillMaxWidth(),
                            fontSize = 18.sp,
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

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp), modifier = Modifier.padding(8.dp)
                ) {
                    items(if (isCosts) viewModel.costsList else viewModel.incomesList) { categoryIcon ->
                        CategoryIconItem(text = categoryIcon.text, icon = categoryIcon.icon)
                    }

                    item {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(16.dp)
                                .clip(RoundedCornerShape(10))
                                .border(2.dp, Color.DarkGray, RoundedCornerShape(10))
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = null,
                                modifier = Modifier.padding(6.dp).size(64.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}