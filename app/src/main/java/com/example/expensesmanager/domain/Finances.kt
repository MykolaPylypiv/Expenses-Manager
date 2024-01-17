package com.example.expensesmanager.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.Color
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.domain.model.CategoryIcon
import com.example.expensesmanager.domain.model.Operation

sealed interface Finances {

    fun categoryIconList(language: Language): List<CategoryIcon>

    fun categoryList(language: Language, alpha: Float): List<Category>

    class Costs(private val operations: List<Operation>) : Finances {

        fun costs(): Int {
            var costs = 0

            operations.map { operation ->
                if (operation.sum < 0) costs += operation.sum
            }

            return -costs
        }

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
            Category(language.food, 25000, 60, Color(0xff498563).copy(alpha)),
            Category(language.cafe, 17289, 30, Color(0xfff0f0aa).copy(alpha)),
            Category(language.transport, 2300, 9, Color(0xfffaad45).copy(alpha)),
            Category(language.health, 500, 5, Color(0xffb44927).copy(alpha)),
            Category(language.pets, 230, 2, Color(0xff932701).copy(alpha)),
            Category(language.family, 2300, 9, Color(0xfffaad45).copy(alpha)),
            Category(language.clothes, 500, 5, Color(0xffb44927).copy(alpha)),
            Category(language.entertainment, 230, 2, Color(0xff932701).copy(alpha))
        )
    }

    class Incomes(private val operations: List<Operation>) : Finances {

        fun incomes(): Int {
            var budget = 0

            operations.map { operation ->
                if (operation.sum > 0) budget += operation.sum
            }

            return budget
        }

        override fun categoryIconList(language: Language) = listOf(
            CategoryIcon(language.salary, Icons.Filled.ShoppingCart),
        )

        override fun categoryList(language: Language, alpha: Float) = listOf(
            Category(language.salary, 230, 100, Color(0xff932701).copy(alpha))
        )
    }
}