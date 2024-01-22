package com.example.expensesmanager.ui.screens.registration

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesmanager.R
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.data.Currency
import com.example.expensesmanager.data.repository.CategoryRepository
import com.example.expensesmanager.data.store.StoreSettings
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.domain.model.Settings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val storeSettings: StoreSettings,
    private val currency: Currency,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    val settings = storeSettings.get()

    var login by mutableStateOf("")
    var password by mutableStateOf("")
    var repeatPassword by mutableStateOf("")

    var selectCurrency: String = ""

    var isCreateCategory by mutableStateOf(true)

    val isCreateList = mutableStateListOf(true, true, true, true, true, true, true, true, true)

    fun sumValue(value: String): String = if (value == "") "0"
    else if (value[0] == '0' && value.length > 1) value.drop(1)
    else value

    fun search(value: String): List<String> {
        val newList = mutableListOf<String>()
        currency.list.map { currency ->
            if (currency.lowercase().contains(value.lowercase())) newList.add(currency)
        }

        return newList.toList()
    }

    fun saveSettings(settings: Settings) = viewModelScope.launch {
        storeSettings.save(settings)
    }

    fun unselectedLanguage(isEnglish: Boolean, language: Language) =
        if (isEnglish) language.unselectedLanguage else language.selectLanguage

    fun selectedLanguage(isEnglish: Boolean, language: Language) =
        if (isEnglish) language.selectLanguage else language.unselectedLanguage

    fun isValidateFirstPage(
        login: String,
        password: String,
        repeatPassword: String,
        context: Context,
        language: Language
    ): Boolean {
        if (login.isEmpty()) {
            Toast.makeText(context, language.loginIsEmpty, Toast.LENGTH_SHORT).show()
            return false
        } else if (login.length > 16) {
            Toast.makeText(context, language.loginIsLonger, Toast.LENGTH_SHORT).show()
            return false
        } else if (password.length < 4 || password.length > 16) {
            Toast.makeText(context, language.passwordIncorrect, Toast.LENGTH_SHORT).show()
            return false
        } else if (password != repeatPassword) {
            Toast.makeText(context, language.passwordNotMatch, Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    fun isValidateThirdPage(currency: String, language: Language, context: Context): Boolean {

        if (currency.isEmpty()) {
            Toast.makeText(context, language.currencySelect, Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    fun defaultCategories(language: Language) = listOf(
        Category(name = language.food, isCosts = true, iconId = R.drawable.food1),
        Category(name = language.cafe, isCosts = true, iconId = R.drawable.cafe1),
        Category(name = language.transport, isCosts = true, iconId = R.drawable.transport1),
        Category(name = language.health, isCosts = true, iconId = R.drawable.health1),
        Category(name = language.pets, isCosts = true, iconId = R.drawable.pets1),
        Category(name = language.family, isCosts = true, iconId = R.drawable.family1),
        Category(name = language.clothes, isCosts = true, iconId = R.drawable.clothes1),
        Category(
            name = language.entertainment, isCosts = true, iconId = R.drawable.entertainment1
        ),
        Category(name = language.salary, isCosts = false, iconId = R.drawable.salary1)
    )

    fun createDefaultCategory(language: Language, isCreateList: List<Boolean>) {
        val categoryIconList = defaultCategories(language).toMutableList()

        categoryIconList.map { category ->
            if (isCreateList[categoryIconList.indexOf(category)]) viewModelScope.launch(Dispatchers.IO) {
                categoryRepository.insert(category)
            }
        }

    }
}