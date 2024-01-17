package com.example.expensesmanager.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.data.Currency
import com.example.expensesmanager.data.repository.CategoryRepository
import com.example.expensesmanager.data.repository.OperationRepository
import com.example.expensesmanager.data.store.StoreSettings
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.domain.model.Settings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val operationRepository: OperationRepository,
    private val categoryRepository: CategoryRepository,
    private val storeSettings: StoreSettings,
    private val currency: Currency,
) : ViewModel() {

    val settings = storeSettings.get()

    val incomesCategories = categoryRepository.incomesCategories()
    val costsCategories = categoryRepository.costsCategories()

    fun deleteALlCategories() {
        viewModelScope.launch {
            categoryRepository.deleteAll()
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            categoryRepository.delete(category)
        }
    }

    fun deleteALlOperations() {
        viewModelScope.launch {
            operationRepository.deleteAll()
        }
    }

    fun saveSettings(settings: Settings) = viewModelScope.launch {
        storeSettings.save(settings)
    }

    fun search(value: String): List<String> {
        val newList = mutableListOf<String>()
        currency.list.map { currency ->
            if (currency.lowercase().contains(value.lowercase())) newList.add(currency)
        }

        return newList.toList()
    }

    fun unselectedLanguage(isEnglish: Boolean, language: Language) =
        if (isEnglish) language.unselectedLanguage else language.selectLanguage

    fun selectedLanguage(isEnglish: Boolean, language: Language) =
        if (isEnglish) language.selectLanguage else language.unselectedLanguage
}