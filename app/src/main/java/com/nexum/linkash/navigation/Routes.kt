package com.nexum.linkash.navigation

sealed class Routes(val route: String) {
    data object Login        : Routes("login")
    data object Register     : Routes("register")
    data object Dashboard    : Routes("dashboard")
    data object Transactions : Routes("transactions")
    data object Chat         : Routes("chat")
}
