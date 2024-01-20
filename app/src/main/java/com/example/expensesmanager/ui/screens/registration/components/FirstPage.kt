package com.example.expensesmanager.ui.screens.registration.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.ui.screens.registration.RegistrationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstPage(language: Language, viewModel: RegistrationViewModel) {
    OutlinedTextField(
        value = viewModel.login,
        onValueChange = {
            viewModel.login = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 48.dp, bottom = 24.dp),
        label = { Text(text = language.login) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = Color.LightGray,
            cursorColor = Color.LightGray
        ),
        singleLine = true
    )

    OutlinedTextField(
        value = viewModel.password,
        onValueChange = {
            viewModel.password = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp, top = 36.dp, start = 24.dp, end = 24.dp),
        label = { Text(text = language.password) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = Color.LightGray,
            cursorColor = Color.LightGray
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true
    )

    OutlinedTextField(
        value = viewModel.repeatPassword,
        onValueChange = {
            viewModel.repeatPassword = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        label = { Text(text = language.repeatPassword) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = Color.LightGray,
            cursorColor = Color.LightGray
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true
    )
}

