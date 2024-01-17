package com.example.expensesmanager.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.Color
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.CategoryOperation
import com.example.expensesmanager.domain.model.CategoryIcon
import com.example.expensesmanager.domain.model.Operation

sealed interface Finances {

    fun categoryIconList(language: Language): List<CategoryIcon>

    fun categoryList(language: Language, alpha: Float): List<CategoryOperation>

    object Costs : Finances {

        override fun categoryIconList(language: Language) = listOf(
            CategoryIcon(language.food, Icons.Filled.ShoppingCart),
            CategoryIcon(language.cafe, Icons.Filled.ShoppingCart),
            CategoryIcon(language.transport, Icons.Filled.ShoppingCart),
            CategoryIcon(language.health, Icons.Filled.ShoppingCart),
            CategoryIcon(language.pets, Icons.Filled.ShoppingCart),
            CategoryIcon(language.family, Icons.Filled.ShoppingCart),
            CategoryIcon(language.clothes, Icons.Filled.ShoppingCart),
            CategoryIcon(language.entertainment, Icons.Filled.ShoppingCart),
        )

        override fun categoryList(language: Language, alpha: Float) = listOf(
            CategoryOperation(language.food, 25000, 60, Color(0xff498563).copy(alpha)),
            CategoryOperation(language.cafe, 17289, 30, Color(0xfff0f0aa).copy(alpha)),
            CategoryOperation(language.transport, 2300, 9, Color(0xfffaad45).copy(alpha)),
            CategoryOperation(language.health, 500, 5, Color(0xffb44927).copy(alpha)),
            CategoryOperation(language.pets, 230, 2, Color(0xff932701).copy(alpha)),
            CategoryOperation(language.family, 2300, 9, Color(0xfffaad45).copy(alpha)),
            CategoryOperation(language.clothes, 500, 5, Color(0xffb44927).copy(alpha)),
            CategoryOperation(language.entertainment, 230, 2, Color(0xff932701).copy(alpha))
        )
    }

    object Incomes : Finances {

        override fun categoryIconList(language: Language) = listOf(
            CategoryIcon(language.salary, Icons.Filled.ShoppingCart),
        )

        override fun categoryList(language: Language, alpha: Float) = listOf(
            CategoryOperation(language.salary, 230, 100, Color(0xff932701).copy(alpha))
        )
    }
}