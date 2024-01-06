package com.example.expensesmanager.navigation

sealed class NavigationTree(var screenRoute: String) {

    object Start : NavigationTree("start")
    object Settings : NavigationTree("settings")
    object Add : NavigationTree("add")
    object Statistics : NavigationTree("statistics")
}