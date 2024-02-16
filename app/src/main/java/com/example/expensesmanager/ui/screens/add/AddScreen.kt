package com.example.expensesmanager.ui.screens.add

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.AddCategory
import com.example.expensesmanager.domain.model.Operation
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.navigation.NavigationTree
import com.example.expensesmanager.ui.composable.AccentFinanceDivider
import com.example.expensesmanager.ui.screens.add.components.AddCategory
import com.example.expensesmanager.ui.screens.add.components.AddCategoryItem
import com.example.expensesmanager.ui.screens.add.components.CategoryIconItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navController: NavController, viewModel: AddViewModel, language: Language
) {
    val alpha = 0.8f

    val context = LocalContext.current

    val settings by viewModel.settings.collectAsState(initial = Settings())

    val incomesCategories by viewModel.incomesCategories.collectAsState(initial = listOf())
    val costsCategories by viewModel.costsCategories.collectAsState(initial = listOf())

    var isCosts by remember { mutableStateOf(true) }

    var sumTextFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = "0"
            )
        )
    }
    var name by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = language.add, color = MaterialTheme.colorScheme.primary, fontSize = 24.sp)
        }, navigationIcon = {
            IconButton(onClick = { navController.navigate(NavigationTree.Start.screenRoute) }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Back")
            }
        }, actions = {
            IconButton(
                onClick = {
                    viewModel.addOperationsList.add(
                        AddCategory(
                            name = viewModel.category,
                            color = viewModel.selectColor,
                            sum = viewModel.machiningOperations(
                                isCosts = isCosts, operation = sumTextFieldValue.text
                            )
                        )
                    )
                }, modifier = Modifier.padding(end = 8.dp)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        })
    }) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .padding(horizontal = 8.dp)
            ) {
                items(viewModel.addOperationsList) { operation ->
                    AddCategoryItem(
                        item = operation, viewModel = viewModel
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            TextField(value = sumTextFieldValue,
                onValueChange = { value ->
                    sumTextFieldValue = TextFieldValue(
                        viewModel.sumValue(value.text),
                        selection = TextRange(sumTextFieldValue.text.length + 1)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                label = { Text(text = settings.currency) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.LightGray,
                    cursorColor = Color.LightGray
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                singleLine = true
            )

            TextField(value = name,
                onValueChange = {
                    name = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                label = { Text(text = language.name) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.LightGray,
                    cursorColor = Color.LightGray
                ),
                singleLine = true
            )

            TextField(value = comment,
                onValueChange = {
                    comment = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
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
                    Column(modifier = Modifier
                        .weight(1F)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(15))
                        .clickable { isCosts = true }
                        .animateContentSize()) {
                        Text(
                            text = language.costs,
                            color = MaterialTheme.colorScheme.primary.copy(viewModel.alpha(!isCosts)),
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth(),
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )

                        AccentFinanceDivider(isCosts = isCosts, alpha = alpha, color = Color.Red)
                    }

                    Column(modifier = Modifier
                        .weight(1F)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(15))
                        .clickable { isCosts = false }
                        .animateContentSize()) {
                        Text(
                            text = language.income,
                            color = MaterialTheme.colorScheme.primary.copy(viewModel.alpha(isCosts)),
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth(),
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )

                        AccentFinanceDivider(isCosts = !isCosts, alpha = alpha, color = Color.Green)
                    }
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp), modifier = Modifier.padding(8.dp)
            ) {
                items(
                    viewModel.financesList(
                        isCosts = isCosts, costs = costsCategories, incomes = incomesCategories
                    )
                ) { categoryIcon ->
                    CategoryIconItem(
                        viewModel = viewModel,
                        text = categoryIcon.name,
                        icon = categoryIcon.iconId,
                        tint = categoryIcon.color
                    )
                }

                item {
                    AddCategory(language = language, viewModel = viewModel, context = context)
                }
            }

            Spacer(modifier = Modifier.weight(1F))

            OutlinedButton(
                onClick = {
                    val operation = Operation(
                        sum = sumTextFieldValue.text.toInt(),
                        name = name,
                        comment = comment,
                        category = viewModel.category
                    )

                    viewModel.insert(
                        operation = operation, isCosts = isCosts, context = context
                    )
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp, vertical = 8.dp)
            ) {
                Text(text = language.add, color = MaterialTheme.colorScheme.primary)
            }

        }
    }
}

