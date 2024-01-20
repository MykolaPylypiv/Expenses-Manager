package com.example.expensesmanager.ui.screens.registration.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.ui.screens.registration.RegistrationViewModel

@Composable
fun SelectLanguage(language: Language, viewModel: RegistrationViewModel, settings: Settings) {
    val dialog = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .height(128.dp)
            .padding(start = 24.dp, end = 24.dp, top = 48.dp, bottom = 24.dp)
            .clip(RoundedCornerShape(10))
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .border(
                1.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(10)
            ), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = language.language,
            fontSize = 20.sp,
            modifier = Modifier
                .weight(1F)
                .padding(start = 15.dp),
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
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Extended floating action button."
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
                        isEnglish = settings.isEnglish, language = language
                    ), color = MaterialTheme.colorScheme.primary
                )
            }, onClick = {
                val newSettings = Settings(
                    isDark = settings.isDark,
                    currency = settings.currency,
                    isEnglish = false
                )

                viewModel.saveSettings(settings = newSettings)
                dialog.value = false
            })
            DropdownMenuItem(text = {
                Text(
                    viewModel.selectedLanguage(
                        isEnglish = settings.isEnglish, language = language
                    ), color = MaterialTheme.colorScheme.primary
                )
            }, onClick = {
                val newSettings = Settings(
                    isDark = settings.isDark,
                    currency = settings.currency,
                    isEnglish = true
                )

                viewModel.saveSettings(settings = newSettings)
                dialog.value = false
            })
        }
    }
}