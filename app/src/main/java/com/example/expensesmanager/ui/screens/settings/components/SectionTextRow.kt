package com.example.expensesmanager.ui.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SectionTextRow(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .height(1.dp)
                .weight(1f)
                .background(MaterialTheme.colorScheme.tertiary)
        )

        Text(
            text = text,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .height(1.dp)
                .weight(1f)
                .background(MaterialTheme.colorScheme.tertiary)
        )
    }
}