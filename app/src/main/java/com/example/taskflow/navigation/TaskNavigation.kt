package com.example.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskflow.TaskFlowApplication
import com.example.taskflow.ui.screens.AddTaskScreen
import com.example.taskflow.ui.screens.EditTaskScreen
import com.example.taskflow.ui.screens.HomeScreen
import com.example.taskflow.viewmodel.TaskViewModel
import com.example.taskflow.viewmodel.TaskViewModelFactory


@Composable
fun TaskNavigation() {

    // Controla a navegação entre as telas
    val navController = rememberNavController()


    // Pegamos o contexto atual do Compose
    // Precisamos dele para acessar a Application
    val context = LocalContext.current


    // Convertendo a Application padrão do Android
    // para nossa Application personalizada
    //
    // Nossa Application guarda o AppContainer,
    // que guarda o Repository
    val application =
        context.applicationContext as TaskFlowApplication


    // Criamos a Factory responsável por criar a ViewModel
    //
    // Passamos o Repository que foi criado no AppContainer
    val factory = TaskViewModelFactory(
        application.appContainer.taskRepository
    )


    // Agora o Android sabe como criar a ViewModel
    //
    // Antes:
    // viewModel()
    //
    // Agora:
    // viewModel(factory = factory)
    //
    // A Factory injeta o Repository no construtor
    val taskViewModel: TaskViewModel = viewModel(
        factory = factory
    )


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

                // Passa a tarefa que será editada
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