package com.example.expensesmanager.ui.screens.statistics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.core.Mapper
import com.example.expensesmanager.data.repository.OperationRepository
import com.example.expensesmanager.data.store.StoreSettings
import com.example.expensesmanager.domain.DateTime
import com.example.expensesmanager.domain.Finances
import com.example.expensesmanager.domain.model.Operation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    mapper: Mapper<DateTime, String>,
    date: DateTime,
    operationRepository: OperationRepository,
    settings: StoreSettings
) : ViewModel() {

    val textDate = mapper.map(date)

    val operations = operationRepository.operation()

    val settings = settings.get()

    var isCosts by mutableStateOf(true)

    fun costsTextColor(isCosts: Boolean, alpha: Float) = if (isCosts) alpha else 0.4f

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

    fun financesList(
        isCosts: Boolean, language: Language, alpha: Float
    ) = if (isCosts) Finances.Costs.categoryList(language, alpha)
    else Finances.Incomes.categoryList(language, alpha)
}