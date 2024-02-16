package com.example.expensesmanager.ui.screens.add.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.domain.model.AddCategory
import com.example.expensesmanager.ui.screens.add.AddViewModel

@Composable
fun OperationsItem(item: AddCategory, viewModel: AddViewModel) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(item.color)
            .padding(8.dp)
            .height(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.name + ": " + item.sum,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "Delete",
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.clickable { viewModel.addOperationsList.remove(item) }
        )
    }
}