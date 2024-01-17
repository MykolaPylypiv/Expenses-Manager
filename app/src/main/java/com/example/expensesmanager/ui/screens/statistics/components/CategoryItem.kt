package com.example.expensesmanager.ui.screens.statistics.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.domain.model.Settings

@Composable
fun CategoryItem(category: Category, settings: Settings) {

    Column(
        horizontalAlignment = Alignment.Start, modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {

        Row {
            Text(
                text = category.name,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp,
                modifier = Modifier.padding(4.dp)
            )

            Text(
                text = "${category.expense} ${settings.currency}",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp,
                modifier = Modifier.padding(4.dp)
            )

            Text(
                text = "(${category.percent}%)",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp,
                modifier = Modifier.padding(4.dp)
            )
        }

        Divider(
            color = category.color,
            thickness = 4.dp,
            modifier = Modifier
                .padding(end = 24.dp)
                .clip(CircleShape)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}