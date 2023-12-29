package com.example.expensesmanager.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expensesmanager.navigation.NavigationTree
import com.example.expensesmanager.ui.screens.start.StartScreen
import com.example.expensesmanager.ui.screens.start.StartViewModel

@Composable
fun ApplicationScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.Start.name) {
        composable(route = NavigationTree.Start.name) {
            val viewModel = hiltViewModel<StartViewModel>()
            StartScreen(navController = navController, viewModel = viewModel)
        }
    }
}