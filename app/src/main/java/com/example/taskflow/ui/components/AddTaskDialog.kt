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

@Composable
fun AddTaskDialog(
    onDismiss: () -> Unit, onConfirm: (String) -> Unit
) {

    var taskTitle by remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(
                text = "Nova tarefa"
            )
        },
        text = {
            TextField(
                value = taskTitle,
                onValueChange = {
                taskTitle = it
            },  label = {
                Text(text = "Título")
            })

        },

        confirmButton = {
            Button(onClick = {
                onConfirm(taskTitle)
            }) {
                Text(
                    text = "Salvar"
                )
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text(
                    text = "Cancelar"
                )
            }
        }
    )
}