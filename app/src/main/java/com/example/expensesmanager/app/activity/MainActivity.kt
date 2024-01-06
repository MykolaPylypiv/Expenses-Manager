package com.example.expensesmanager.app.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.ui.screens.ApplicationScreen
import com.example.expensesmanager.ui.theme.ExpensesManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<MainViewModel>()

            val settings = viewModel.settings.collectAsState(Settings())
            val language = if (settings.value.isEnglish) Language.English else Language.Ukraine

            ExpensesManagerTheme(darkTheme = settings.value.isDark) {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ApplicationScreen(language = language)
                }
            }
        }
    }
}