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
    val operations: String,
    val categories: String,
    val selectIcon: String,
    val createCategory: String,
    val createCategories: String,
    val setIcon: String,
    val create: String,
    val deleteCategories: String,
    val deleteAll: String,
    val yes: String,
    val no: String,
    val deleteAllOperations: String,
    val textDeleteAllOperations: String,
    val textDeleteAllCategories: String,
    val textDeleteCategory: String,
    val empty: String,
    val months: List<String>,
    val login: String,
    val budget: String,
    val registration: String,
    val password: String,
    val repeatPassword: String,
    val next: String,
    val back: String,
    val complete: String,
    val loginIsEmpty: String,
    val loginIsLonger: String,
    val passwordIncorrect: String,
    val passwordNotMatch: String,
    val currencySelect: String,
    val nickname: String,
    val forgotPassword: String,
    val signIn: String,
    val changePassword: String,
    val account: String,
    val description: String,
    val exit: String,
    val dark: String,
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
        operations = "Operations",
        categories = "Categories",
        selectIcon = "Select icon",
        createCategory = "Create category",
        createCategories = "Create categories",
        setIcon = "Set icon",
        create = "Create",
        deleteCategories = "Delete categories",
        deleteAll = "Delete all",
        yes = "Yes",
        no = "No",
        deleteAllOperations = "Delete all operations",
        textDeleteAllOperations = "Are you sure you want to delete all operations ?",
        textDeleteAllCategories = "Are you sure you want to delete all categories ?",
        textDeleteCategory = "Are you sure you want to delete this category ?",
        empty = "Empty",
        months = listOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        ),
        login = "Login",
        budget = "Budget",
        registration = "Registration",
        password = "Password",
        repeatPassword = "Repeat password",
        next = "Next",
        back = "Back",
        complete = "Complete",
        loginIsEmpty = "Enter your login",
        loginIsLonger = "Enter a login with less than 16 characters",
        passwordIncorrect = "Enter a password with 4 to 16 characters",
        passwordNotMatch = "Passwords do not match",
        currencySelect = "Select currency",
        nickname = "Nickname",
        forgotPassword = "Forgot password ?",
        signIn = "Sign in",
        changePassword = "Change password",
        account = "Account",
        description = "Description",
        exit = "Exit",
        dark = "Dark",
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
        categories = "Категорії",
        selectIcon = "Виберіть зображення",
        createCategory = "Створити категорію",
        createCategories = "Створити категорії",
        setIcon = "Встановити зображення",
        create = "Створити",
        deleteCategories = "Видалити категорії",
        deleteAll = "Видалити всі",
        yes = "Так",
        no = "Ні",
        deleteAllOperations = "Видалення всіх операцій",
        textDeleteAllOperations = "Ви впевнені, що хочете видалити всі операції ?",
        textDeleteAllCategories = "Ви впевнені, що хочете видалити всі категорії ?",
        textDeleteCategory = "Ви впевнені, що хочете видалити категорію ?",
        empty = "Пусто",
        months = listOf(
            "Січень",
            "Лютий",
            "Березень",
            "Квітень",
            "Травень",
            "Червень",
            "Липень",
            "Серпень",
            "Вересень",
            "Жовтень",
            "Листопад",
            "Грудень",
        ),
        login = "Логін",
        budget = "Бюджет",
        registration = "Реєстрація",
        password = "Пароль",
        repeatPassword = "Повторіть пароль",
        next = "Далі",
        back = "Назад",
        complete = "Завершити",
        loginIsEmpty = "Введіть логін",
        loginIsLonger = "Введіть логін в якому менше 16 символів",
        passwordIncorrect = "Введіть пароль в якому від 4 до 16 симолів",
        passwordNotMatch = "Паролі не збігаються",
        currencySelect = "Виберіть валюту",
        nickname = "Логін",
        forgotPassword = "Забули пароль ?",
        signIn = "Зареєструватись",
        changePassword = "Змінити пароль",
        account = "Акаунт",
        description = "Опис",
        exit = "Вихід",
        dark = "Темна",
    )
}
