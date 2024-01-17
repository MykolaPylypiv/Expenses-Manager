package com.example.expensesmanager.ui.screens.settings.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.R
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.ui.screens.settings.SettingsViewModel

@Composable
fun CategoryItem(category: Category, viewModel: SettingsViewModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 24.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = category.name, color = MaterialTheme.colorScheme.primary, fontSize = 18.sp
        )

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            painter = painterResource(id = category.iconId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.size(25.dp)
        )

        Spacer(modifier = Modifier.weight(1F))

        IconButton(
            onClick = { viewModel.deleteCategory(category = category) }, modifier = Modifier.size(35.dp)
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.delete_icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}