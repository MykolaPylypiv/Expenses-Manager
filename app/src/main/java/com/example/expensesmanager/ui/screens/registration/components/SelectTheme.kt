package com.example.expensesmanager.ui.screens.registration.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.ui.screens.registration.RegistrationViewModel

@Composable
fun SelectTheme(language: Language, settings: Settings, viewModel: RegistrationViewModel) {
    Row(
        modifier = Modifier
            .height(104.dp)
            .padding(bottom = 12.dp, top = 36.dp, start = 24.dp, end = 24.dp)
            .clip(RoundedCornerShape(10))
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .border(
                1.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(10)
            ), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = language.theme,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 15.dp)
        )

        Spacer(modifier = Modifier.weight(1F))

        Switch(
            checked = settings.isDark, onCheckedChange = { isDark ->
                val newSettings = Settings(
                    isDark = isDark,
                    currency = settings.currency,
                    isEnglish = settings.isEnglish
                )

                viewModel.saveSettings(settings = newSettings)
            }, modifier = Modifier.padding(end = 24.dp)
        )
    }
}