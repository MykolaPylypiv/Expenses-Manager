package com.example.expensesmanager.ui.screens.incomes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IncomesViewModel @Inject constructor() : ViewModel() {

    val costsList = listOf(
        CategoryIcon("Food", Icons.Filled.ShoppingCart),
        CategoryIcon("Cafe", Icons.Filled.ShoppingCart),
        CategoryIcon("Transport", Icons.Filled.ShoppingCart),
        CategoryIcon("Health", Icons.Filled.ShoppingCart),
        CategoryIcon("Pets", Icons.Filled.ShoppingCart),
        CategoryIcon("Family", Icons.Filled.ShoppingCart),
        CategoryIcon("Clothes", Icons.Filled.ShoppingCart),
        CategoryIcon("Entertainment", Icons.Filled.ShoppingCart),
    )

    val incomesList = listOf(
        CategoryIcon("Salary", Icons.Filled.ShoppingCart),
    )
}

data class CategoryIcon(val text: String, val icon: ImageVector)