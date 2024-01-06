package com.example.expensesmanager.ui.screens.incomes.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.ui.screens.incomes.AddViewModel

@Composable
fun CategoryIconItem(viewModel: AddViewModel, text: String, icon: ImageVector) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(10))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(10))
            .clickable { viewModel.newOperation.category = text }
    ) {
        Icon(imageVector = icon, contentDescription = text, modifier = Modifier.padding(8.dp))

        Text(text = text, color = MaterialTheme.colorScheme.primary, fontSize = 16.sp, modifier = Modifier.padding(8.dp))
    }
}