package com.example.expensesmanager.domain

import androidx.compose.ui.graphics.Color
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.domain.model.CategoryOperation
import com.example.expensesmanager.domain.model.Operation
import javax.inject.Inject
import kotlin.math.roundToInt

interface CategoryList {

    val colors: List<Color>

    fun categoryOperationList(
        language: Language, alpha: Float, categories: List<Category>, operations: List<Operation>
    ): List<CategoryOperation>

    fun chartValues(categories: List<Category>, operations: List<Operation>): List<Float>

    class Base @Inject constructor() : CategoryList {

        override val colors = listOf(
            Color(0xffF9E380),
            Color(0xffFECC87),
            Color(0xffFFA894),
            Color(0xffF98089),
            Color(0xffF9B7C5),
            Color(0xffC192B8),
            Color(0xffA8B7E0),
            Color(0xff87D1F6),
            Color(0xff81E8E5),
            Color(0xffB7E5A5),
            Color(0xffEAEFAC),
            Color(0xffFFF490),
            Color(0xff498563).copy(alpha),
            Color(0xffb44927).copy(alpha),
            Color(0xff932701).copy(alpha),
            Color(0xfffaad45).copy(alpha),
        )

        override fun categoryOperationList(
            language: Language,
            alpha: Float,
            categories: List<Category>,
            operations: List<Operation>
        ): List<CategoryOperation> {
            val categoriesOperation = mutableListOf<CategoryOperation>()
            var count = 0

            var budget = 1

            operations.map { operation ->
                budget += operation.sum
            }

            categories.map { category ->
                var expense = 0.0

                operations.map { operation ->
                    if (operation.category == category.name) expense += operation.sum
                }

                categoriesOperation.add(
                    CategoryOperation(
                        name = category.name,
                        color = colors[count],
                        expense = expense.toInt(),
                        percent = (expense / budget * 100).roundToInt(),
                    )
                )

                count += 1
            }

            return categoriesOperation
        }

        override fun chartValues(
            categories: List<Category>, operations: List<Operation>
        ): List<Float> {
            val chartValues = mutableListOf<Float>()

            categories.map { category ->
                var sum = 0

                operations.map { operation ->
                    if (category.name == operation.category) {
                        sum += operation.sum
                    }
                }

                chartValues.add(sum.toFloat())
            }

            return chartValues
        }


        companion object {
            const val alpha = 0.8F
        }
    }
}