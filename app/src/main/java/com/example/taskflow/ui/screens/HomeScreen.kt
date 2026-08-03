package com.example.taskflow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.taskflow.model.Task
import com.example.taskflow.ui.components.DeleteTaskDialog
import com.example.taskflow.ui.components.TaskItem
import com.example.taskflow.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
    onAddClick: () -> Unit,
    onEditClick: (Int) -> Unit
) {

    // Observa a lista de tarefas.
    //
    // collectAsState() transforma o StateFlow da ViewModel
    // em um State do Compose.
    //
    // Sempre que o Flow emitir uma nova lista,
    // essa tela será recomposta automaticamente.
    val taskList by taskViewModel.tasks.collectAsState()

    // Guarda temporariamente qual tarefa
    // o usuário deseja excluir.
    //
    // Enquanto for null,
    // o Dialog permanece fechado.
    var taskToDelete by remember {
        mutableStateOf<Task?>(null)
    }

    // Scaffold é a estrutura principal
    // de uma tela Material Design.
    //
    // Ele organiza:
    //
    // - TopBar
    // - BottomBar
    // - FloatingActionButton
    // - Snackbar
    // - Conteúdo principal
    Scaffold(

        // Barra superior da tela.
        topBar = {
            TopAppBar(
                title = {
                    Text("Minhas tarefas")
                },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        },

        // Botão flutuante.
        //
        // Quando clicado, navega para
        // a tela de criação de tarefa.
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar tarefa"
                )
            }
        }

    ) { padding ->

        // O Scaffold fornece um PaddingValues.
        //
        // Esse padding evita que o conteúdo fique
        // escondido atrás da TopBar ou do FAB.
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            // LazyColumn é equivalente ao
            // ListView.builder do Flutter.
            //
            // Ela renderiza apenas os itens
            // visíveis na tela.
            LazyColumn {

                // items percorre toda a lista
                // de tarefas.
                items(taskList) { task ->

                    TaskItem(

                        task = task,

                        // Usuário marcou ou desmarcou
                        // uma tarefa.
                        //
                        // Como nossa Task é imutável,
                        // usamos copy() para criar
                        // uma nova instância alterando
                        // apenas o campo completed.
                        onTaskChecked = {

                            taskViewModel.updateTask(

                                task.copy(
                                    completed = !task.completed
                                )

                            )

                        },

                        // Navega para a tela
                        // de edição.
                        onEdit = {

                            onEditClick(task.id)

                        },

                        // Apenas abre o Dialog.
                        //
                        // A exclusão acontece
                        // somente após confirmação.
                        onDelete = {

                            taskToDelete = task

                        }

                    )

                }

            }

        }

    }

    // O Dialog só aparece
    // quando existe uma tarefa
    // selecionada.
    if (taskToDelete != null) {

        DeleteTaskDialog(

            // Usuário cancelou.
            onDismiss = {

                taskToDelete = null

            },

            // Usuário confirmou.
            onConfirm = {

                // Remove a tarefa
                // do banco Room.
                taskViewModel.deleteTask(
                    taskToDelete!!
                )

                // Fecha o Dialog.
                taskToDelete = null

            }

        )

    }

}