package com.example.expensesmanager.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.navigation.NavigationTree
import com.example.expensesmanager.ui.screens.forgotPassword.ForgotPasswordScreen
import com.example.expensesmanager.ui.screens.forgotPassword.ForgotPasswordViewModel
import com.example.expensesmanager.ui.screens.settings.SettingsScreen
import com.example.expensesmanager.ui.screens.settings.SettingsViewModel
import com.example.expensesmanager.ui.screens.incomes.AddScreen
import com.example.expensesmanager.ui.screens.incomes.AddViewModel
import com.example.expensesmanager.ui.screens.login.LoginScreen
import com.example.expensesmanager.ui.screens.login.LoginViewModel
import com.example.expensesmanager.ui.screens.registration.RegistrationScreen
import com.example.expensesmanager.ui.screens.registration.RegistrationViewModel
import com.example.expensesmanager.ui.screens.start.StartScreen
import com.example.expensesmanager.ui.screens.start.StartViewModel
import com.example.expensesmanager.ui.screens.statistics.StatisticsScreen
import com.example.expensesmanager.ui.screens.statistics.StatisticsViewModel

@Composable
fun ApplicationScreen(language: Language) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.Login.screenRoute) {
        composable(route = NavigationTree.Login.screenRoute) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(navController = navController, viewModel = viewModel, language = language)
        }
        composable(route = NavigationTree.Registration.screenRoute) {
            val viewModel = hiltViewModel<RegistrationViewModel>()
            RegistrationScreen(navController = navController, viewModel = viewModel, language = language)
        }
        composable(route = NavigationTree.ForgotPassword.screenRoute) {
            val viewModel = hiltViewModel<ForgotPasswordViewModel>()
            ForgotPasswordScreen(navController = navController, viewModel = viewModel, language = language)
        }
        composable(route = NavigationTree.Start.screenRoute) {
            val viewModel = hiltViewModel<StartViewModel>()
            StartScreen(navController = navController, viewModel = viewModel, language = language)
        }
        composable(route = NavigationTree.Settings.screenRoute) {
            val viewModel = hiltViewModel<SettingsViewModel>()
            SettingsScreen(navController = navController, viewModel = viewModel, language = language)
        }
        composable(route = NavigationTree.Add.screenRoute) {
            val viewModel = hiltViewModel<AddViewModel>()
            AddScreen(navController = navController, viewModel = viewModel, language = language)
        }
        composable(route = NavigationTree.Statistics.screenRoute) {
            val viewModel = hiltViewModel<StatisticsViewModel>()
            StatisticsScreen(navController = navController, viewModel = viewModel, language = language)
        }
    }
}
