package com.example.expensesmanager.ui.screens.settings.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.ui.screens.settings.SettingsViewModel

@Composable
fun ClearOperations(language: Language, viewModel: SettingsViewModel) {
    var openDialog by remember { mutableStateOf(false)  }

    TextButton(modifier = Modifier.padding(start = 10.dp, end = 25.dp),
        onClick = { openDialog = true }) {
        Text(
            text = language.clearOperations,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }

    if (openDialog) {

        AlertDialog(
            onDismissRequest = {
                openDialog = false
            },
            title = {
                Text(text = language.deleteAllOperations)
            },
            text = {
                Text(language.textDeleteAllOperations)
            },
            confirmButton = {
                Button(

                    onClick = {
                        openDialog = false
                        viewModel.deleteALlOperations()
                    }) {
                    Text(language.yes)
                }
            },
            dismissButton = {
                Button(

                    onClick = {
                        openDialog = false
                    }) {
                    Text(language.no)
                }
            }
        )
    }
}