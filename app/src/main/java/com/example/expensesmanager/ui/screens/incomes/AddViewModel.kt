package com.example.expensesmanager.ui.screens.incomes

import android.content.Context
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesmanager.data.repository.OperationRepository
import com.example.expensesmanager.data.store.StoreSettings
import com.example.expensesmanager.domain.model.CategoryIcon
import com.example.expensesmanager.domain.model.Operation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repository: OperationRepository,
    storeSettings: StoreSettings
) : ViewModel() {

    val settings = storeSettings.get()

    val newOperation: Operation = Operation()

    fun insert(operation: Operation = newOperation, context: Context, isCosts: Boolean) {
        if (isCosts) operation.sum = -operation.sum

        if (newOperation.sum == 0) Toast.makeText(
            context, "Please, enter sum that is different from 0", Toast.LENGTH_SHORT
        ).show()
        else if (newOperation.name.isEmpty()) Toast.makeText(
            context, "Please, enter name", Toast.LENGTH_SHORT
        ).show()
        else if (newOperation.category.isEmpty()) Toast.makeText(
            context, "Please, select category", Toast.LENGTH_SHORT
        ).show()
        else viewModelScope.launch(Dispatchers.IO) { repository.insert(operation) }
    }
}