package com.example.expensesmanager.domain.model

import androidx.compose.ui.graphics.Color

data class Category(
    val name: String, val expense: Int, val percent: Int, val color: Color
)
