package com.example.taskflow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskflow.model.Task
import com.example.taskflow.viewmodel.TaskViewModel


@Composable
fun EditTaskScreen(
    taskId: Int,
    taskViewModel: TaskViewModel,
    onTaskUpdated: () -> Unit
) {
    // Guarda a tarefa que veio do banco
    var task by remember {
        mutableStateOf<Task?>(null)
    }
    // Guarda o texto que o usuário está editando
    var title by remember {
        mutableStateOf("")
    }
    // Executa quando a tela recebe um novo taskId
    //
    // Aqui fazemos a busca no banco
    LaunchedEffect(taskId) {
        taskViewModel.getTaskById(taskId) { result ->
            task = result
            title = result?.title ?: ""
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Editar tarefa"
        )
        TextField(
            value = title,
            onValueChange = { newTitle ->
                title = newTitle
            },
            label = {
                Text(
                    "Título"
                )
            }
        )
        Button(
            onClick = {
                taskViewModel.updateTask(
                    Task(
                        id = taskId,
                        title = title,
                        // mantém o estado atual
                        completed = task?.completed ?: false
                    )
                )
                onTaskUpdated()
            }
        ) {
            Text(
                "Salvar"
            )
        }
    }
}