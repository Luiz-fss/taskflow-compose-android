package com.example.taskflow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskflow.viewmodel.TaskViewModel

@Composable
fun EditTaskScreen(
    taskId: Int,
    taskViewModel: TaskViewModel,
    onTaskUpdated: () -> Unit
) {

    val task = taskViewModel.getTaskById(taskId)

    var title by remember(task) {
        mutableStateOf(task?.title ?: "")
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
                Text("Título")
            }
        )

        Button(
            onClick = {
                taskViewModel.updateTask(
                    id = taskId,
                    title = title
                )

                onTaskUpdated()
            }
        ) {
            Text("Salvar")
        }
    }
}