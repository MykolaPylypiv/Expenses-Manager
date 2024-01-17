package com.example.expensesmanager.ui.screens.start

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.expensesmanager.core.Mapper
import com.example.expensesmanager.data.repository.OperationRepository
import com.example.expensesmanager.data.store.StoreSettings
import com.example.expensesmanager.domain.DateTime
import com.example.expensesmanager.domain.model.Operation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    mapper: Mapper<DateTime, String>,
    private val date: DateTime,
    operationRepository: OperationRepository,
    settings: StoreSettings
) : ViewModel() {

    val textDate = mapper.map(date)

    val operations = operationRepository.operation()

    val settings = settings.get()

    fun openCloseShape(expanded: Boolean) = if (!expanded) 25 else 15

    fun openCloseHeight(expanded: Boolean) = if (expanded) 460.dp else 200.dp

    fun openCloseAlpha(expanded: Boolean) = if (expanded) 0f else 1f

    fun openCloseSpacer(expanded: Boolean, start: Dp) = if (!expanded) start else 0.dp

    fun operationItemColor(income: Int) = if (income >= 0) Color.Green else Color.Red

    fun budgetColor(percent: Int): Color {
        return if (percent > 75) {
            Color(0xff51c374)
        } else if (percent > 55) {
            Color(0xffc0eb34)
        } else if (percent > 40) {
            Color.Yellow
        } else if (percent > 30) {
            Color(0xffeb9c34)
        } else if (percent > 25) {
            Color(0xffeb7134)
        } else {
            Color.Red
        }
    }

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