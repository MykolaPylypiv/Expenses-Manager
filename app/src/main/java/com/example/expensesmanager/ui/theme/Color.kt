package com.example.expensesmanager.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val darkColorScheme = darkColorScheme(
    primary = Color.White,
    secondary = Color.Black,
    tertiary = Color.LightGray,
    onBackground = Color(0xFF363837),
    onTertiary = Color(0xFFb38bc4).copy(0.5F)
)

val lightColorScheme = lightColorScheme(
    primary = Color.Black,
    secondary = Color.White,
    tertiary = Color.DarkGray,
    onBackground = Color.White,
    onTertiary = Color(0xFFb38bc4)
)