package com.example.taskflow.navigation
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskflow.ui.screens.AddTaskScreen
import com.example.taskflow.ui.screens.HomeScreen
import com.example.taskflow.viewmodel.TaskViewModel

//desenha a estrutura de navegação
@Composable
fun TaskNavigation() {

    //controle de navegação, sabe a tela atual,qual tela devo ir e voltar
    val navController = rememberNavController()

    //pegando a viewmodel2
    val taskViewModel: TaskViewModel = viewModel()

    NavHost(
        //NavHost usa esse navegador aqui
        navController = navController,
        //Tela inicial quando o app abrir. Pode ser colocado condicional caso precise
        startDestination = Screen.Home.route
    ) {

        //cadastrando uma tela
        composable(
            route = Screen.Home.route
        ) {
            //Tela que será criada
            HomeScreen(
                taskViewModel = taskViewModel,
                onAddClick = {
                    navController.navigate(Screen.Add.route)
                }
            )
        }
        composable(Screen.Add.route) {
            AddTaskScreen(
                taskViewModel = taskViewModel
            )
        }
    }
}