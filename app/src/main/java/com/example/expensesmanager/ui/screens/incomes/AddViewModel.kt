package com.example.expensesmanager.ui.screens.incomes

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.data.repository.OperationRepository
import com.example.expensesmanager.data.store.StoreSettings
import com.example.expensesmanager.domain.Finances
import com.example.expensesmanager.domain.model.Operation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repository: OperationRepository, storeSettings: StoreSettings
) : ViewModel() {

    val settings = storeSettings.get()

    var category = ""

    fun insert(operation: Operation, context: Context, isCosts: Boolean) {
        if (isCosts) operation.sum = -operation.sum

        if (operation.sum == 0) Toast.makeText(
            context, "Please, enter sum that is different from 0", Toast.LENGTH_SHORT
        ).show()
        else if (operation.name.isEmpty()) Toast.makeText(
            context, "Please, enter name", Toast.LENGTH_SHORT
        ).show()
        else if (operation.category.isEmpty()) Toast.makeText(
            context, "Please, select category", Toast.LENGTH_SHORT
        ).show()
        else viewModelScope.launch(Dispatchers.IO) { repository.insert(operation) }
    }

    fun sumValue(value: String): String = if (value == "") "0"
    else if (value[0] == '0' && value.length > 1) value.drop(1)
    else value

    fun financesList(isCosts: Boolean, language: Language) =
        if (isCosts) Finances.Costs.categoryIconList(language) else Finances.Incomes.categoryIconList(language)
}