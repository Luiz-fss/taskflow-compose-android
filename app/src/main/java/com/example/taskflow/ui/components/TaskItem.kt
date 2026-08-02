package com.example.taskflow.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskflow.model.Task

@Composable
fun TaskItem(
    task: Task,
    onTaskChecked: () -> Unit
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
    }
}