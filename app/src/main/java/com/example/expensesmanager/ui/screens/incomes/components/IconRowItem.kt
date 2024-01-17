package com.example.expensesmanager.ui.screens.incomes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.expensesmanager.domain.ListIcons
import com.example.expensesmanager.ui.screens.incomes.AddViewModel

@Composable
fun IconRowItem(icons: ListIcons, viewModel: AddViewModel) {
    val fontSize = 24.sp
    val padding = 16.dp

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = icons.name, color = MaterialTheme.colorScheme.primary, fontSize = fontSize
        )

        Spacer(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .height(1.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary)
        )
    }

    Row {
        icons.list.forEach { id ->
            IconRowButton(
                modifier = Modifier
                    .weight(1F)
                    .padding(padding), id = id, viewModel = viewModel
            )
        }
    }
}

@Composable
fun IconRowButton(modifier: Modifier, id: Int, viewModel: AddViewModel) {
    IconButton(onClick = {
        viewModel.iconId = id
        viewModel.iconDialog = false
    }, modifier = modifier) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.tertiary
        )
    }
}