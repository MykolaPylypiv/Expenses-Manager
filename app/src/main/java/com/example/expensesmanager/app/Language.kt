package com.example.expensesmanager.app

sealed class Language(
    val name: String,
    val commentNotNecessarily: String,
    val costs: String,
    val income: String,
    val food: String,
    val cafe: String,
    val transport: String,
    val health: String,
    val pets: String,
    val family: String,
    val clothes: String,
    val entertainment: String,
    val add: String,
    val salary: String,
    val currency: String,
    val search: String,
    val language: String,
    val settings: String,
    val selectLanguage: String,
    val unselectedLanguage: String,
    val theme: String,
    val clearOperations: String,
    val monthlyBudget: String,
    val date: String,
    val beginning: String,
    val avgCostsPerDay: String,
    val idealExpensesPerDay: String,
    val statistics: String,
    val operations: String
) {

    object English : Language(
        name = "Name",
        commentNotNecessarily = "Comment (not necessarily)",
        costs = "Costs",
        income = "Income",
        cafe = "Cafe",
        clothes = "Clothes",
        entertainment = "Entertainment",
        family = "Family",
        food = "Food",
        health = "Health",
        pets = "Pets",
        transport = "Transport",
        add = "Add",
        salary = "Salary",
        currency = "Currency",
        search = "Search",
        language = "Language",
        settings = "Settings",
        selectLanguage = "English",
        unselectedLanguage = "Ukrainian",
        theme = "Theme",
        clearOperations = "Clear operations",
        monthlyBudget = "Monthly budget",
        date = "Date",
        beginning = "Beginning",
        avgCostsPerDay = "Avg costs per day",
        idealExpensesPerDay = "Ideal expenses per day",
        statistics = "Statistics",
        operations = "Operations"
    )

    object Ukraine : Language(
        name = "Назва",
        commentNotNecessarily = "Опис (не обов'язково)",
        costs = "Витрати",
        income = "Дохід",
        transport = "Транспорт",
        pets = "Улюбленець",
        health = "Здоров'я",
        food = "Їжа",
        family = "Сім'я",
        entertainment = "Розваги",
        clothes = "Одяг",
        cafe = "Кафе",
        add = "Додати",
        salary = "Зарплата",
        currency = "Валюта",
        search = "Пошук",
        language = "Мова",
        settings = "Налаштування",
        selectLanguage = "Українська",
        unselectedLanguage = "Англійська",
        theme = "Тема",
        clearOperations = "Очистити транзакції",
        monthlyBudget = "Місячний бюджет",
        date = "Дата",
        beginning = "Дохід",
        avgCostsPerDay = "Сер. витрати в день",
        idealExpensesPerDay = "Ідеальні витрати в день",
        statistics = "Статистика",
        operations = "Транзакції",
    )
}