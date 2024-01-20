package com.example.expensesmanager.ui.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.navigation.NavigationTree
import com.example.expensesmanager.ui.screens.settings.SettingsViewModel

@Composable
fun ChangeCategories(
    language: Language,
    navController: NavController,
    viewModel: SettingsViewModel,
    costsCategories: List<Category>,
    incomesCategories: List<Category>
) {
    var categoryDialog by remember { mutableStateOf(false) }

    TextButton(
        modifier = Modifier.padding(start = 10.dp, end = 25.dp),
        onClick = { categoryDialog = true }) {
        Text(
            text = language.changeCategories,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }

    if (categoryDialog) Dialog(onDismissRequest = { categoryDialog = false }) {

        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.navigate(NavigationTree.Start.screenRoute) }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Text(
                    text = language.changeCategories,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .height(1.dp)
                                .weight(1f)
                                .background(MaterialTheme.colorScheme.tertiary)
                        )

                        Text(
                            text = language.costs,
                            fontSize = 22.sp,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .height(1.dp)
                                .weight(1f)
                                .background(MaterialTheme.colorScheme.tertiary)
                        )
                    }
                }

                items(
                    viewModel.categoriesAudit(
                        list = costsCategories, language = language
                    )
                ) { category ->
                    CategoryItem(category = category, viewModel = viewModel, language = language)
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .height(1.dp)
                                .weight(1f)
                                .background(MaterialTheme.colorScheme.tertiary)
                        )

                        Text(
                            text = language.income,
                            fontSize = 22.sp,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .height(1.dp)
                                .weight(1f)
                                .background(MaterialTheme.colorScheme.tertiary)
                        )
                    }
                }

                items(
                    viewModel.categoriesAudit(
                        list = incomesCategories, language = language
                    )
                ) { category ->
                    CategoryItem(category = category, viewModel = viewModel, language = language)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = {
                    viewModel.deleteALlCategories()
                    categoryDialog = false
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = language.deleteAll, color = MaterialTheme.colorScheme.primary)

            }
        }
    }
}