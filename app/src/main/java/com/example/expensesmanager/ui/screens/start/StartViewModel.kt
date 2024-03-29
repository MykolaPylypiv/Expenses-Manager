package com.example.expensesmanager.ui.screens.start

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.core.Mapper
import com.example.expensesmanager.data.repository.OperationRepository
import com.example.expensesmanager.data.store.StoreSettings
import com.example.expensesmanager.domain.DateTime
import com.example.expensesmanager.domain.model.MapToUiParameters
import com.example.expensesmanager.domain.model.Operation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val mapper: Mapper<MapToUiParameters, String>,
    private val date: DateTime,
    operationRepository: OperationRepository,
    settings: StoreSettings
) : ViewModel() {

    val operations = operationRepository.operation()

    val settings = settings.get()

    var expanded by mutableStateOf(false)

    fun textDate(language: Language) = mapper.map(MapToUiParameters(dateTime = date, language = language))

    fun openCloseShape(expanded: Boolean) = if (!expanded) 20 else 10

    fun openCloseHeight(expanded: Boolean) = if (expanded) 460.dp else 230.dp

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

        return if ((sum / budget * 100).toInt() >= 0) {
            (sum / budget * 100).toInt()
        } else 0
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

    fun avgCosts(operations: List<Operation>, language: Language): Int {
        val day = date.month(year = date.year, month = date.monthNumber - 1, language = language)

        var costs = 0

        operations.map { operation ->
            if (operation.sum < 0) costs += operation.sum
        }

        return -(costs / day.days)
    }

    fun idealCosts(operations: List<Operation>, language: Language): Int {
        val day = date.month(year = date.year, month = date.monthNumber - 1, language = language)

        var budget = 0

        operations.map { operation ->
            if (operation.sum > 0) budget += operation.sum
        }

        return (budget / day.days)
    }
}