package com.example.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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

    // Controla a navegação entre as telas
    val navController = rememberNavController()

    // Obtém a ViewModel gerenciada pelo Hilt.
    //
    // Antes precisávamos:
    //
    // Application
    // ↓
    // AppContainer
    // ↓
    // Repository
    // ↓
    // ViewModelFactory
    // ↓
    // viewModel(factory)
    //
    // Agora basta pedir a ViewModel.
    //
    // O Hilt é responsável por criar
    // toda a cadeia de dependências:
    //
    // ViewModel
    // ↓
    // Repository
    // ↓
    // DAO
    // ↓
    // Room Database
    val taskViewModel: TaskViewModel = hiltViewModel()

    // Estrutura de navegação do app
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        // Tela inicial
        composable(
            route = Screen.Home.route
        ) {

            HomeScreen(
                taskViewModel = taskViewModel,

                // Navega para adicionar tarefa
                onAddClick = {

                    navController.navigate(
                        Screen.Add.route
                    )

                },

                // Navega para editar tarefa passando o ID
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

        // Tela de adicionar tarefa
        composable(
            route = Screen.Add.route
        ) {

            AddTaskScreen(
                taskViewModel = taskViewModel,

                // Volta para a tela anterior
                // depois de salvar
                onTaskSaved = {

                    navController.popBackStack()

                }
            )
        }

        // Tela de editar tarefa
        composable(
            route = Screen.Edit.route,

            // Define que essa tela recebe um argumento
            // chamado taskId
            arguments = listOf(

                navArgument("taskId") {

                    // O ID é um número inteiro
                    type = NavType.IntType

                }

            )
        ) { backStackEntry ->

            // Recupera o ID enviado pela navegação
            val taskId =
                backStackEntry
                    .arguments
                    ?.getInt("taskId")
                    ?: 0

            EditTaskScreen(

                // Passa o ID da tarefa
                // que será editada
                taskId = taskId,

                taskViewModel = taskViewModel,

                // Depois de atualizar,
                // volta para a tela anterior
                onTaskUpdated = {

                    navController.popBackStack()

                }
            )
        }
    }
}