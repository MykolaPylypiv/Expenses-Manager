package com.example.expensesmanager.ui.screens.settings.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.expensesmanager.ui.screens.settings.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Currency(borderColor: Color, viewModel: SettingsViewModel) {
    val stateDialog = remember { mutableStateOf(false) }
    val selectCurrency = remember { mutableStateOf("UAH") }
    val icon = Icons.Filled.KeyboardArrowDown

    var search by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .height(50.dp)
            .padding(start = 10.dp, end = 40.dp)
            .clip(CircleShape)
            .border(1.dp, borderColor, CircleShape)
            .clickable { stateDialog.value = true },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Currency",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 15.dp)
        )

        Spacer(modifier = Modifier.weight(1F))


        Text(
            text = selectCurrency.value, color = Color.White
        )

        AnimatedContent(
            transitionSpec = {
                (slideInVertically { height -> height } + fadeIn()).togetherWith(slideOutVertically { height -> -height } + fadeOut())
            }, targetState = icon, label = ""
        ) { target ->
            Icon(
                imageVector = target, contentDescription = "Open", tint = Color.White
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
    }

    if (stateDialog.value) {
        Dialog(onDismissRequest = { stateDialog.value = false }) {
            Column(modifier = Modifier.padding(vertical = 48.dp)) {
                TextField(value = search,
                    onValueChange = {
                        search = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Search")},
                    colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.background)
                )

                LazyColumn(
                    modifier = Modifier
                        .clip(RoundedCornerShape(3))
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth(),
                ) {
                    viewModel.search(search).forEach { text ->
                        item {
                            CurrencyDialogItem(text = text, onClick = {
                                selectCurrency.value = text.substring(text.length - 8, text.length - 4)
                                stateDialog.value = false
                            })
                        }
                    }

                }
            }
        }
    }
}