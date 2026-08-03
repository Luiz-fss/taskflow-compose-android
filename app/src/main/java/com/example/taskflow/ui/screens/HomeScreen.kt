package com.example.taskflow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskflow.model.Task
import com.example.taskflow.ui.components.AddTaskDialog
import com.example.taskflow.ui.components.TaskItem
import com.example.taskflow.viewmodel.TaskViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
    onAddClick: () -> Unit
) {
    val taskList by taskViewModel.tasks.collectAsState()

    var taskToDelete by remember {
        mutableStateOf<Task?>(null)
    }

    var showAddTaskDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAddClick()
                }
            ) {
                Text(text = "+")
            }
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
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
                        },

                        onDelete = {
                            taskToDelete = task
                        },

                        onEdit = {
                            // por enquanto nada
                        }
                    )
                }
            }
        }
    }

    if (showAddTaskDialog) {
        AddTaskDialog(
            onDismiss = {
                showAddTaskDialog = false
            },
            onConfirm = { title ->
                taskViewModel.addTask(title)
                showAddTaskDialog = false
            }
        )
    }
}