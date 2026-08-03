package com.example.taskflow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.taskflow.model.Task
import com.example.taskflow.ui.components.DeleteTaskDialog
import com.example.taskflow.ui.components.TaskItem
import com.example.taskflow.viewmodel.TaskViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
    onAddClick: () -> Unit,
    onEditClick: (Int) -> Unit
) {

    val taskList by taskViewModel.tasks.collectAsState()

    var taskToDelete by remember {
        mutableStateOf<Task?>(null)
    }

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
                    },

                    onEdit = {
                        onEditClick(task.id)
                    },

                    onDelete = {
                        taskToDelete = task
                    }
                )
            }
        }
    }


    if (taskToDelete != null) {

        DeleteTaskDialog(

            onDismiss = {
                taskToDelete = null
            },

            onConfirm = {

                taskViewModel.deleteTask(
                    taskToDelete!!.id
                )

                taskToDelete = null
            }
        )
    }
}