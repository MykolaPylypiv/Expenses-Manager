package com.example.expensesmanager.ui.screens.add.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.ui.screens.add.AddViewModel

@Composable
fun CategoryIconItem(viewModel: AddViewModel, text: String, icon: Int, tint: Color) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(10))
            .height(114.dp)
            .border(1.dp, MaterialTheme.colorScheme.onTertiary, RoundedCornerShape(10))
            .clickable {
                viewModel.category = text
                viewModel.selectColor = tint
            }) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier.padding(8.dp).size(64.dp),
            tint = tint
        )

        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}