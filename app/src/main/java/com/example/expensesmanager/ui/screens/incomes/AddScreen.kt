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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.CategoryIcon
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.navigation.NavigationTree
import com.example.expensesmanager.ui.screens.incomes.components.CategoryIconItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navController: NavController, viewModel: AddViewModel, language: Language
) {
    var isCosts by remember { mutableStateOf(true) }
    val alpha = 0.8f

    var sum by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }

    val context = LocalContext.current

    val settings = viewModel.settings.collectAsState(initial = Settings())

    val costsList = listOf(
        CategoryIcon(language.food, Icons.Filled.ShoppingCart),
        CategoryIcon(language.cafe, Icons.Filled.ShoppingCart),
        CategoryIcon(language.transport, Icons.Filled.ShoppingCart),
        CategoryIcon(language.health, Icons.Filled.ShoppingCart),
        CategoryIcon(language.pets, Icons.Filled.ShoppingCart),
        CategoryIcon(language.family, Icons.Filled.ShoppingCart),
        CategoryIcon(language.clothes, Icons.Filled.ShoppingCart),
        CategoryIcon(language.entertainment, Icons.Filled.ShoppingCart),
    )

    val incomesList = listOf(
        CategoryIcon(language.salary, Icons.Filled.ShoppingCart),
    )

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = language.add, color = MaterialTheme.colorScheme.primary, fontSize = 24.sp)
        }, navigationIcon = {
            IconButton(onClick = { navController.navigate(NavigationTree.Start.screenRoute) }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Back")
            }
        }, actions = {
            IconButton(onClick = {
                viewModel.newOperation.sum = if (sum.isNotEmpty()) sum.toInt() else 0
                viewModel.newOperation.name = name
                viewModel.newOperation.comment = comment
                viewModel.insert(context = context, isCosts = isCosts)
            }, modifier = Modifier.padding(end = 8.dp)) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        })
    }) { padding ->

        Column(modifier = Modifier.padding(padding)) {
            TextField(value = sum,
                onValueChange = {
                    sum = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                label = { Text(text = settings.value.currency) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.LightGray,
                    cursorColor = Color.LightGray
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            )

            TextField(value = name,
                onValueChange = {
                    name = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                label = { Text(text = language.name) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.LightGray,
                    cursorColor = Color.LightGray
                )
            )

            TextField(value = comment,
                onValueChange = {
                    comment = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                label = { Text(text = language.commentNotNecessarily) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.LightGray,
                    cursorColor = Color.LightGray
                )
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
                            text = language.costs,
                            color = MaterialTheme.colorScheme.primary,
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
                            text = language.income,
                            color = MaterialTheme.colorScheme.primary,
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
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp), modifier = Modifier.padding(8.dp)
            ) {
                items(if (isCosts) costsList else incomesList) { categoryIcon ->
                    CategoryIconItem(
                        viewModel = viewModel, text = categoryIcon.text, icon = categoryIcon.icon
                    )
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
                            modifier = Modifier
                                .padding(6.dp)
                                .size(64.dp)
                        )
                    }
                }
            }
        }
    }
}

