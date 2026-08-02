package com.example.taskflow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskflow.model.Task
import com.example.taskflow.ui.components.TaskItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    val taskList = listOf(
        Task(
            id = 1,
            title = "Estudar Compose",
            completed = false
        ),
        Task(
            id = 2,
            title = "Criar TaskFlow",
            completed = true
        ),
        Task(
            id = 3,
            title = "Aprender Kotlin",
            completed = false
        )
    )
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Minhas tarefas"
        )
        LazyColumn {
            items(taskList) { task ->
                TaskItem(
                    task = task
                )
            }
        }
    }
}