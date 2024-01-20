package com.example.expensesmanager.ui.screens.registration.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.ui.screens.registration.RegistrationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdPage(viewModel: RegistrationViewModel, language: Language, settings: Settings) {
    var budget by remember { mutableStateOf("0") }

    OutlinedTextField(
        value = budget,
        onValueChange = { value ->
            budget = viewModel.sumValue(value)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 48.dp, bottom = 24.dp),
        label = { Text(text = language.budget) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = Color.LightGray,
            cursorColor = Color.LightGray
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        singleLine = true
    )

    Currency(
        viewModel = viewModel, settings = settings, language = language
    )

    Row(
        modifier = Modifier
            .height(104.dp)
            .padding(24.dp)
            .clip(RoundedCornerShape(10))
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .border(
                1.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(10)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = language.createCategories,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 15.dp)
        )

        Spacer(modifier = Modifier.weight(1F))

        Checkbox(
            checked = viewModel.isCreateCategory,
            onCheckedChange = { viewModel.isCreateCategory = it },
        )
    }
}