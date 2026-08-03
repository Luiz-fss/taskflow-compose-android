package com.example.taskflow.navigation

sealed class Screen(
    val route: String
) {
    object Home : Screen("home")
    object Add : Screen("add")
    object Edit : Screen("edit/{taskId}")
}