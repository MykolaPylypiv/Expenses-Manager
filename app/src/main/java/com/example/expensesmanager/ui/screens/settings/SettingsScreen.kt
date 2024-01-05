package com.example.expensesmanager.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensesmanager.navigation.NavigationTree
import com.example.expensesmanager.ui.screens.settings.components.Currency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController, viewModel: SettingsViewModel
) {
    val dialog = remember { mutableStateOf(false) }
    val isDarkState = remember { mutableStateOf(false) }
    val isEnglishState = remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Settings", color = Color.White, fontSize = 24.sp)
        }, navigationIcon = {
            IconButton(onClick = { navController.navigate(NavigationTree.Start.screenRoute) }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Back")
            }
        })
    }) { padding ->

        Column(modifier = Modifier.padding(padding)) {

            Row(
                modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Language",
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1F),
                    color = Color.White
                )

                ExtendedFloatingActionButton(
                    modifier = Modifier
                        .weight(1F)
                        .height(30.dp),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp),
                    onClick = { dialog.value = true },
                    icon = {
                        Icon(
                            Icons.Filled.KeyboardArrowDown, "Extended floating action button."
                        )
                    },
                    text = {
                        androidx.compose.material3.Text(
                            text = if (isEnglishState.value) "English" else "Українська",
                            fontSize = 18.sp
                        )
                    },
                )

                DropdownMenu(
                    expanded = dialog.value,
                    onDismissRequest = { dialog.value = false },
                    offset = DpOffset(x = 230.dp, y = 10.dp)
                ) {
                    DropdownMenuItem(text = { Text("Ukrainian", color = Color.White) },
                        onClick = { dialog.value = false })
                    DropdownMenuItem(text = { Text("English", color = Color.White) }, onClick = {
                        isEnglishState.value = !isEnglishState.value
                        dialog.value = false
                    })
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            Row(
                modifier = Modifier.padding(start = 25.dp, end = 25.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Theme", fontSize = 20.sp, color = Color.White)

                Spacer(modifier = Modifier.weight(1F))

                Switch(
                    modifier = Modifier.padding(end = 80.dp),
                    checked = isDarkState.value,
                    onCheckedChange = {
                        isDarkState.value = it
                    },
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Currency(borderColor = Color.Transparent, viewModel = viewModel)
        }
    }
}