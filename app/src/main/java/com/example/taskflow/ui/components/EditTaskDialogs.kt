package com.example.taskflow.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.taskflow.model.Task

@Composable
fun EditTaskDialog(
    task: Task,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {

    var taskTitle by remember {
        mutableStateOf(task.title)
    }

    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },

        title = {
            Text(
                text = "Editar tarefa"
            )
        },

        text = {
            TextField(
                value = taskTitle,
                onValueChange = {
                    taskTitle = it
                },
                label = {
                    Text(
                        text = "Título"
                    )
                }
            )
        },

        confirmButton = {
            Button(
                onClick = {
                    onConfirm(taskTitle)
                }
            ) {
                Text(
                    text = "Salvar"
                )
            }
        },

        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(
                    text = "Cancelar"
                )
            }
        }
    )
}