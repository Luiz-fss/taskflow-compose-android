package com.example.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskflow.ui.screens.AddTaskScreen
import com.example.taskflow.ui.screens.EditTaskScreen
import com.example.taskflow.ui.screens.HomeScreen
import com.example.taskflow.viewmodel.TaskViewModel

@Composable
fun TaskNavigation() {

    val navController = rememberNavController()

    val taskViewModel: TaskViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(
            route = Screen.Home.route
        ) {

            HomeScreen(
                taskViewModel = taskViewModel,

                onAddClick = {
                    navController.navigate(Screen.Add.route)
                },

                onEditClick = { taskId ->

                    navController.navigate(
                        Screen.Edit.route.replace(
                            "{taskId}",
                            taskId.toString()
                        )
                    )

                }
            )
        }


        composable(
            route = Screen.Add.route
        ) {

            AddTaskScreen(
                taskViewModel = taskViewModel,

                onTaskSaved = {
                    navController.popBackStack()
                }
            )
        }


        composable(
            route = Screen.Edit.route,
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val taskId = backStackEntry
                .arguments
                ?.getInt("taskId") ?: 0


            EditTaskScreen(
                taskId = taskId,
                taskViewModel = taskViewModel,

                onTaskUpdated = {
                    navController.popBackStack()
                }
            )
        }
    }
}