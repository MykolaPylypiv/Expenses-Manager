package com.example.expensesmanager.ui.screens.settings.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CurrencyDialogItem(text: String, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxSize()
                .height(48.dp)
                .wrapContentSize(Alignment.Center),
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}