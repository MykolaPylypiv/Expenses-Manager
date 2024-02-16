package com.example.expensesmanager.ui.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.ui.screens.settings.SettingsViewModel

@Composable
fun DeleteCategories(
    language: Language,
    viewModel: SettingsViewModel,
    costsCategories: List<Category>,
    incomesCategories: List<Category>
) {
    var categoryDialog by remember { mutableStateOf(false) }
    var deleteAllDialog by remember { mutableStateOf(false)  }

    TextButton(modifier = Modifier.padding(start = 10.dp, end = 25.dp),
        onClick = { categoryDialog = true }) {
        Text(
            text = language.deleteCategories,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
            style = TextStyle.Default
        )
    }

    if (categoryDialog) Dialog(onDismissRequest = { categoryDialog = false }) {

        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    SectionTextRow(text = language.costs)
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

                    SectionTextRow(text = language.income)
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
                    deleteAllDialog = true
                }, modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1F)
            ) {
                Text(text = language.deleteAll, color = MaterialTheme.colorScheme.primary)
            }
        }
    }

    if (deleteAllDialog) {

        AlertDialog(
            onDismissRequest = {
                deleteAllDialog = false
            },
            title = {
                Text(text = language.deleteAll)
            },
            text = {
                Text(language.textDeleteAllCategories)
            },
            confirmButton = {
                Button(

                    onClick = {
                        deleteAllDialog = false
                        categoryDialog = false
                        viewModel.deleteALlCategories()
                    }) {
                    Text(language.yes)
                }
            },
            dismissButton = {
                Button(

                    onClick = {
                        deleteAllDialog = false
                    }) {
                    Text(language.no)
                }
            }
        )
    }
}