package com.example.expensesmanager.ui.screens.statistics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.core.Mapper
import com.example.expensesmanager.data.repository.CategoryRepository
import com.example.expensesmanager.data.repository.OperationRepository
import com.example.expensesmanager.data.store.StoreSettings
import com.example.expensesmanager.domain.DateTime
import com.example.expensesmanager.domain.CategoryList
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.domain.model.MapToUiParameters
import com.example.expensesmanager.domain.model.Operation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val mapper: Mapper<MapToUiParameters, String>,
    private val date: DateTime,
    operationRepository: OperationRepository,
    categoryRepository: CategoryRepository,
    settings: StoreSettings,
    private val categoryList: CategoryList.Base
) : ViewModel() {

    val operations = operationRepository.operation()
    val incomesCategories = categoryRepository.incomesCategories()
    val costsCategories = categoryRepository.costsCategories()

    val settings = settings.get()

    var isCosts by mutableStateOf(true)

    val colors = categoryList.colors

    fun textDate(language: Language) = mapper.map(MapToUiParameters(dateTime = date, language = language))

    fun alpha(isCosts: Boolean, alpha: Float) = if (isCosts) alpha else 0.4f

    fun incomes(operations: List<Operation>): Int {
        var budget = 0

        operations.map { operation ->
            if (operation.sum > 0) budget += operation.sum
        }

        return budget
    }

    fun costs(operations: List<Operation>): Int {
        var costs = 0

        operations.map { operation ->
            if (operation.sum < 0) costs += operation.sum
        }

        return -costs
    }

    fun categoryOperationList(
        isCosts: Boolean,
        language: Language,
        alpha: Float,
        costsCategories: List<Category>,
        incomesCategories: List<Category>,
        operations: List<Operation>
    ) = if (isCosts) categoryList.categoryOperationList(
        language, alpha, costsCategories, operations
    )
    else categoryList.categoryOperationList(language, alpha, incomesCategories, operations)

    fun chartValues(
        isCosts: Boolean,
        costsCategories: List<Category>,
        incomesCategories: List<Category>,
        operations: List<Operation>
    ) = if (isCosts) categoryList.chartValues(categories = costsCategories, operations = operations)
        else categoryList.chartValues(categories = incomesCategories, operations = operations)
}