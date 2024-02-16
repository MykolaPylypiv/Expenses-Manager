package com.example.expensesmanager.ui.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.R
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.ui.screens.settings.SettingsViewModel

@Composable
fun CategoryItem(category: Category, viewModel: SettingsViewModel, language: Language) {
    var deleteCategoryDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 24.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = category.name, color = MaterialTheme.colorScheme.primary, fontSize = 18.sp
        )

        if (!viewModel.isEmptyList(category.name, language = language)) {
            Spacer(modifier = Modifier.weight(1F))

            IconButton(
                onClick = { deleteCategoryDialog = true }, modifier = Modifier.size(35.dp)
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.delete_icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

    if (deleteCategoryDialog) {

        AlertDialog(onDismissRequest = {
            deleteCategoryDialog = false
        }, title = {
            Text(text = "${language.deleteCategories} ${category.name}")
        }, text = {
            Text(language.textDeleteCategory)
        }, confirmButton = {
            Button(

                onClick = {
                    deleteCategoryDialog = false
                    viewModel.deleteCategory(category = category)
                }) {
                Text(language.yes)
            }
        }, dismissButton = {
            Button(

                onClick = {
                    deleteCategoryDialog = false
                }) {
                Text(language.no)
            }
        })
    }
}