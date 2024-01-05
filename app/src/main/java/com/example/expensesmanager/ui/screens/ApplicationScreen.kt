package com.example.expensesmanager.ui.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expensesmanager.navigation.NavigationTree
import com.example.expensesmanager.ui.screens.settings.SettingsScreen
import com.example.expensesmanager.ui.screens.settings.SettingsViewModel
import com.example.expensesmanager.ui.screens.incomes.IncomesScreen
import com.example.expensesmanager.ui.screens.incomes.IncomesViewModel
import com.example.expensesmanager.ui.screens.start.StartScreen
import com.example.expensesmanager.ui.screens.start.StartViewModel
import com.example.expensesmanager.ui.screens.statistics.StatisticsScreen
import com.example.expensesmanager.ui.screens.statistics.StatisticsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ApplicationScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.Start.screenRoute) {
        composable(route = NavigationTree.Start.screenRoute) {
            val viewModel = hiltViewModel<StartViewModel>()
            StartScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = NavigationTree.Settings.screenRoute) {
            val viewModel = hiltViewModel<SettingsViewModel>()
            SettingsScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = NavigationTree.Incomes.screenRoute) {
            val viewModel = hiltViewModel<IncomesViewModel>()
            IncomesScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = NavigationTree.Statistics.screenRoute) {
            val viewModel = hiltViewModel<StatisticsViewModel>()
            StatisticsScreen(navController = navController, viewModel = viewModel)
        }
    }
}
