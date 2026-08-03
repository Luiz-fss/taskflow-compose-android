package com.example.taskflow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskflow.viewmodel.TaskViewModel

@Composable
fun AddTaskScreen(
    taskViewModel: TaskViewModel
) {

    var title by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Nova tarefa"
        )

        TextField(
            value = title,
            onValueChange = {
                //it é o parâmetro automático da função lambda.
                title = it
            },
            label = {
                Text(
                    text = "Título"
                )
            }
        )

        Button(
            onClick = {
                taskViewModel.addTask(title)
            }
        ) {
            Text(
                text = "Salvar"
            )
        }
    }
}