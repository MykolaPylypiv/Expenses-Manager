package com.example.expensesmanager.ui.screens.add

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.data.repository.CategoryRepository
import com.example.expensesmanager.data.repository.OperationRepository
import com.example.expensesmanager.data.store.StoreSettings
import com.example.expensesmanager.domain.CategoryList
import com.example.expensesmanager.domain.ListIcons
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.domain.model.CategoryIcon
import com.example.expensesmanager.domain.model.Operation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val operationRepository: OperationRepository,
    private val categoryRepository: CategoryRepository,
    val categoryList: CategoryList.Base,
    storeSettings: StoreSettings
) : ViewModel() {

    var iconDialog by mutableStateOf(false)

    val settings = storeSettings.get()

    val incomesCategories = categoryRepository.incomesCategories()
    val costsCategories = categoryRepository.costsCategories()

    var category = ""

    var iconId = 0

    fun listIcons(language: Language) = listOf(
        ListIcons.Food(language, categoryList.colors[0]),
        ListIcons.Cafe(language, categoryList.colors[1]),
        ListIcons.Transport(language, categoryList.colors[2]),
        ListIcons.Health(language, categoryList.colors[3]),
        ListIcons.Pets(language, categoryList.colors[4]),
        ListIcons.Family(language, categoryList.colors[5]),
        ListIcons.Clothes(language, categoryList.colors[6]),
        ListIcons.Entertainment(language, categoryList.colors[7]),
        ListIcons.Salary(language, categoryList.colors[8])
    )

    fun createCategory(category: Category, context: Context) {
        if (category.name.isEmpty()) Toast.makeText(
            context, "Please, enter name category", Toast.LENGTH_SHORT
        ).show()
        else if (category.iconId == 0) Toast.makeText(
            context, "Please, select icon", Toast.LENGTH_SHORT
        ).show()
        else viewModelScope.launch(Dispatchers.IO) { categoryRepository.insert(category) }
    }

    fun insert(operation: Operation, context: Context, isCosts: Boolean) {
        if (isCosts) operation.sum = -operation.sum

        if (operation.sum == 0) Toast.makeText(
            context, "Please, enter sum that is different from 0", Toast.LENGTH_SHORT
        ).show()
        else if (operation.name.isEmpty()) Toast.makeText(
            context, "Please, enter name", Toast.LENGTH_SHORT
        ).show()
        else if (operation.name == "Empty") Toast.makeText(
            context, "Category cannot have a name \"Empty\"", Toast.LENGTH_SHORT
        ).show()
        else if (operation.category.isEmpty()) Toast.makeText(
            context, "Please, select category", Toast.LENGTH_SHORT
        ).show()
        else viewModelScope.launch(Dispatchers.IO) { operationRepository.insert(operation) }
    }

    fun sumValue(value: String): String = if (value == "") "0"
    else if (value[0] == '0' && value.length > 1) value.drop(1)
    else value

    fun financesList(isCosts: Boolean, incomes: List<Category>, costs: List<Category>): List<CategoryIcon> {
        val list = if (isCosts) costs else incomes
        val newList = mutableListOf<CategoryIcon>()

        list.map { category ->
            newList.add(
                CategoryIcon(
                    name = category.name,
                    color = categoryList.colors[newList.size],
                    iconId = category.iconId
                )
            )
        }

        return newList.toList()
    }

    fun alpha(isCosts: Boolean) = if (!isCosts) 1F else 0.4F
}