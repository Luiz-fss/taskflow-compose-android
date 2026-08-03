package com.example.taskflow.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskflow.ui.components.AddTaskDialog
import com.example.taskflow.ui.screens.HomeScreen
import com.example.taskflow.viewmodel.TaskViewModel

@Composable
fun TaskFlowApp() {
    val taskViewModel: TaskViewModel = viewModel()

    var showAddTaskDialog by remember {
        mutableStateOf(false)
    }
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            showAddTaskDialog = true
        }) {
            Text(text = "+")
        }
    }) { innerPadding ->
        HomeScreen(
            modifier = Modifier.padding(innerPadding),
            taskViewModel
        )
    }

    if (showAddTaskDialog) {
        AddTaskDialog(
            onDismiss = {
                showAddTaskDialog = false
            },
            onConfirm = {tittle ->
                taskViewModel.addTask(tittle)
                showAddTaskDialog = false
            }
        )

    }
}