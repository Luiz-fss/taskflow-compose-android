package com.example.taskflow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskflow.ui.components.TaskItem
import com.example.taskflow.viewmodel.TaskViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel = viewModel()
) {
    val taskList by taskViewModel.tasks.collectAsState()
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Minhas tarefas"
        )
        LazyColumn {
            items(taskList) { task ->
                TaskItem(
                    task = task,
                    onTaskChecked = {
                        taskViewModel.toggleTask(task.id)
                    }
                )
            }
        }
    }
}