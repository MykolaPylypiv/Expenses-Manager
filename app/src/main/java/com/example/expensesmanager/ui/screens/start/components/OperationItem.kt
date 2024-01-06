package com.example.expensesmanager.ui.screens.start.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Currency

@Composable
fun OperationItem(icon: ImageVector, name: String, category: String, income: Int, currency: String) {
    val color = if (income >= 0) Color.Green else Color.Red

    Row(
        modifier = Modifier.padding(vertical = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Category",
            modifier = Modifier.padding(start = 16.dp)
        )

        Column {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = name,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
            )

            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = category,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp),
            text = "$income $currency",
            color = color.copy(0.7F),
            fontSize = 20.sp,
            textAlign = TextAlign.Right
        )
    }
}
