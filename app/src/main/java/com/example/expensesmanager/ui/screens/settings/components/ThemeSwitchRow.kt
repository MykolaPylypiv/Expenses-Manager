package com.example.expensesmanager.ui.screens.settings.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.ui.screens.settings.SettingsViewModel

@Composable
fun ThemeSwitchRow(language: Language, settings: Settings, viewModel: SettingsViewModel) {
    Row(
        modifier = Modifier.padding(start = 25.dp, end = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = language.dark,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.weight(1F))

        Switch(
            modifier = Modifier.padding(end = 80.dp),
            checked = settings.isDark,
            onCheckedChange = { isDark ->
                val newSettings = Settings(
                    isDark = isDark,
                    currency = settings.currency,
                    isEnglish = settings.isEnglish
                )

                viewModel.saveSettings(settings = newSettings)
            },
        )
    }
}