package com.example.taskflow.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DeleteTaskDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },

        title = {
            Text(
                text = "Excluir tarefa"
            )
        },

        text = {
            Text(
                text = "Tem certeza que deseja excluir esta tarefa?"
            )
        },

        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                }
            ) {
                Text(
                    text = "Excluir"
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