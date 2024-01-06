package com.example.expensesmanager.ui.screens.start

import androidx.lifecycle.ViewModel
import com.example.expensesmanager.core.Mapper
import com.example.expensesmanager.data.repository.OperationRepository
import com.example.expensesmanager.data.store.StoreSettings
import com.example.expensesmanager.domain.Date
import com.example.expensesmanager.domain.model.Operation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    mapper: Mapper<Date, String>,
    private val date: Date,
    operationRepository: OperationRepository,
    settings: StoreSettings
) : ViewModel() {

    val textDate = mapper.map(date)

    val operations = operationRepository.operation()

    val settings = settings.get()

    fun budget(operations: List<Operation>): Int {
        var sum = 0

        operations.map { operation ->
            sum += operation.sum
        }

        return sum
    }

    fun percentBudget(operations: List<Operation>): Int {
        var sum = 0.0
        var budget = 0.0

        operations.map { operation ->
            sum += operation.sum

            if (operation.sum > 0) budget += operation.sum
        }

        return (sum / budget * 100).toInt()
    }

    fun beginning(operations: List<Operation>): Int {
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

    fun avgCosts(operations: List<Operation>): Int {
        val day = date.month(year = date.year, month = date.monthNumber - 1)

        var costs = 0

        operations.map { operation ->
            if (operation.sum < 0) costs += operation.sum
        }

        return -(costs / day.days)
    }

    fun idealCosts(operations: List<Operation>): Int {
        val day = date.month(year = date.year, month = date.monthNumber - 1)

        var budget = 0

        operations.map { operation ->
            if (operation.sum > 0) budget += operation.sum
        }

        return (budget / day.days)
    }
}