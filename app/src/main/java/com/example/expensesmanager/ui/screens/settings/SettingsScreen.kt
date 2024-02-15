package com.example.expensesmanager.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.navigation.NavigationTree
import com.example.expensesmanager.ui.screens.settings.components.ClearOperations
import com.example.expensesmanager.ui.screens.settings.components.Currency
import com.example.expensesmanager.ui.screens.settings.components.DeleteCategories
import com.example.expensesmanager.ui.screens.settings.components.SectionTextRow
import com.example.expensesmanager.ui.screens.settings.components.SelectLanguage
import com.example.expensesmanager.ui.screens.settings.components.ThemeSwitchRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController, viewModel: SettingsViewModel, language: Language
) {
    val settings = viewModel.settings.collectAsState(Settings())

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

            SectionTextRow(text = language.operations)

            Spacer(modifier = Modifier.height(40.dp))

            Currency(
                viewModel = viewModel,
                settings = settings.value,
                language = language
            )

            Spacer(modifier = Modifier.height(50.dp))

            ClearOperations(language = language, viewModel = viewModel)

            Spacer(modifier = Modifier.height(50.dp))

            DeleteCategories(
                language = language,
                viewModel = viewModel,
                costsCategories = costsCategories,
                incomesCategories = incomesCategories
            )

            Spacer(modifier = Modifier.height(50.dp))

            SectionTextRow(text = language.theme)

            Spacer(modifier = Modifier.height(40.dp))

            SelectLanguage(viewModel = viewModel, settings = settings.value, language = language)

            Spacer(modifier = Modifier.height(50.dp))

            ThemeSwitchRow(language = language, settings = settings.value, viewModel = viewModel)
        }
    }
}