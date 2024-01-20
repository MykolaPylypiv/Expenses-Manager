package com.example.expensesmanager.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.navigation.NavigationTree
import com.example.expensesmanager.ui.screens.settings.components.ChangeCategories
import com.example.expensesmanager.ui.screens.settings.components.ClearOperations
import com.example.expensesmanager.ui.screens.settings.components.Currency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController, viewModel: SettingsViewModel, language: Language
) {
    val settings = viewModel.settings.collectAsState(Settings())

    val dialog = remember { mutableStateOf(false) }

    val incomesCategories by viewModel.incomesCategories.collectAsState(initial = listOf())
    val costsCategories by viewModel.costsCategories.collectAsState(initial = listOf())

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = language.settings,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
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
                    text = language.language,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1F),
                    color = MaterialTheme.colorScheme.primary
                )

                ExtendedFloatingActionButton(
                    modifier = Modifier
                        .weight(1F)
                        .height(30.dp),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp),
                    onClick = { dialog.value = true },
                    icon = {
                        Icon(
                            Icons.Filled.KeyboardArrowDown, "Extended floating action button."
                        )
                    },
                    text = {
                        Text(
                            text = language.selectLanguage, fontSize = 18.sp
                        )
                    },
                )

                DropdownMenu(
                    expanded = dialog.value,
                    onDismissRequest = { dialog.value = false },
                    offset = DpOffset(x = 230.dp, y = 10.dp)
                ) {
                    DropdownMenuItem(text = {
                        Text(
                            viewModel.unselectedLanguage(
                                isEnglish = settings.value.isEnglish, language = language
                            ), color = MaterialTheme.colorScheme.primary
                        )
                    }, onClick = {
                        val newSettings = Settings(
                            isDark = settings.value.isDark,
                            currency = settings.value.currency,
                            isEnglish = false
                        )

                        viewModel.saveSettings(settings = newSettings)
                        dialog.value = false
                    })
                    DropdownMenuItem(text = {
                        Text(
                            viewModel.selectedLanguage(
                                isEnglish = settings.value.isEnglish, language = language
                            ), color = MaterialTheme.colorScheme.primary
                        )
                    }, onClick = {
                        val newSettings = Settings(
                            isDark = settings.value.isDark,
                            currency = settings.value.currency,
                            isEnglish = true
                        )

                        viewModel.saveSettings(settings = newSettings)
                        dialog.value = false
                    })
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            Row(
                modifier = Modifier.padding(start = 25.dp, end = 25.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = language.theme,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.weight(1F))

                Switch(
                    modifier = Modifier.padding(end = 80.dp),
                    checked = settings.value.isDark,
                    onCheckedChange = { isDark ->
                        val newSettings = Settings(
                            isDark = isDark,
                            currency = settings.value.currency,
                            isEnglish = settings.value.isEnglish
                        )

                        viewModel.saveSettings(settings = newSettings)
                    },
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Currency(
                borderColor = Color.Transparent,
                viewModel = viewModel,
                settings = settings.value,
                language = language
            )

            Spacer(modifier = Modifier.height(50.dp))

            ClearOperations(language = language, viewModel = viewModel)

            Spacer(modifier = Modifier.height(50.dp))

            ChangeCategories(
                language = language,
                navController = navController,
                viewModel = viewModel,
                costsCategories = costsCategories,
                incomesCategories = incomesCategories
            )

        }
    }
}