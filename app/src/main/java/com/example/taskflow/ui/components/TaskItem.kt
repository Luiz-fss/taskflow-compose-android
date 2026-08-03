package com.example.taskflow.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskflow.model.Task

@Composable
fun TaskItem(
    task: Task,
    onTaskChecked: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        Checkbox(
            checked = task.completed,
            onCheckedChange = {
                onTaskChecked()
            }
        )

        Text(
            text = task.title,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = onEdit
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar tarefa"
            )
        }

        IconButton(
            onClick = onDelete
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Excluir tarefa"
            )
        }
    }
}