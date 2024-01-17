package com.example.expensesmanager.ui.screens.incomes.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.ui.screens.incomes.AddViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCategory(language: Language, viewModel: AddViewModel, context: Context) {
    var stateDialog by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }

    var isCosts by remember { mutableStateOf(true) }

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(10))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(10))
            .clickable { stateDialog = true }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = language.add,
            modifier = Modifier.padding(8.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = language.add,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
    }

    if (stateDialog) {
        Dialog(onDismissRequest = { stateDialog = false }) {

            Column(
                modifier = Modifier
                    .padding(vertical = 48.dp)
                    .background(MaterialTheme.colorScheme.background),
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { stateDialog = false },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    Text(
                        text = language.createCategory,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 22.sp
                    )
                }

                TextField(
                    value = name,
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
                        cursorColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray
                    )
                )

                Row(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = language.costs,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.weight(1F))

                    Switch(
                        modifier = Modifier.padding(end = 80.dp),
                        checked = isCosts,
                        onCheckedChange = {
                            isCosts = it
                        },
                    )
                }

                OutlinedButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                    onClick = { viewModel.iconDialog = true }) { Text(text = language.setIcon) }

                Spacer(modifier = Modifier.height(80.dp))

                OutlinedButton(
                    onClick = {
                        stateDialog = false
                        viewModel.createCategory(
                            category = Category(
                                isCosts = isCosts, iconId = viewModel.iconId, name = name
                            ), context = context
                        )
                        name = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                ) {
                    Text(text = language.create, color = MaterialTheme.colorScheme.primary)
                }
            }

        }
    }

    if (viewModel.iconDialog) {
        val padding = 16.dp

        Dialog(onDismissRequest = { viewModel.iconDialog = false }) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    item {
                        Text(
                            text = language.selectIcon,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        Spacer(modifier = Modifier.height(32.dp))
                    }

                    items(viewModel.listIcons(language)) { icons ->
                        IconRowItem(icons = icons, viewModel = viewModel)

                        Spacer(modifier = Modifier.height(padding))
                    }
                }
            }
        }
    }
}