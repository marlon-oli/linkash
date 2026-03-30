package com.nexum.linkash.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nexum.linkash.feature.auth.presentation.LoginScreen
import com.nexum.linkash.feature.auth.presentation.RegisterScreen
import com.nexum.linkash.feature.chat.presentation.ChatScreen
import com.nexum.linkash.feature.dashboard.presentation.DashboardScreen
import com.nexum.linkash.feature.transactions.presentation.TransactionsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Routes.Login.route,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.Login.route) {
            LoginScreen(
                onLoginSuccess  = { navController.navigate(Routes.Dashboard.route) { popUpTo(Routes.Login.route) { inclusive = true } } },
                onGoToRegister  = { navController.navigate(Routes.Register.route) },
            )
        }
        composable(Routes.Register.route) {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate(Routes.Login.route) { popUpTo(Routes.Register.route) { inclusive = true } } },
                onGoToLogin       = { navController.popBackStack() },
            )
        }
        composable(Routes.Dashboard.route) {
            DashboardScreen(
                onGoToTransactions = { navController.navigate(Routes.Transactions.route) },
                onGoToChat         = { navController.navigate(Routes.Chat.route) },
            )
        }
        composable(Routes.Transactions.route) {
            TransactionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.Chat.route) {
            ChatScreen(onBack = { navController.popBackStack() })
        }
    }
}
