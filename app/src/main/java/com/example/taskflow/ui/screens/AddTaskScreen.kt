package com.example.taskflow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskflow.model.Task
import com.example.taskflow.viewmodel.TaskViewModel


@Composable
fun AddTaskScreen(
    taskViewModel: TaskViewModel,
    onTaskSaved: () -> Unit
) {
    // Estado local do campo de texto
    var title by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Adicionar tarefa"
        )
        OutlinedTextField(
            // Valor atual do campo
            value = title,
            // Executa toda vez que o usuário digita
            onValueChange = {
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
                // Criamos o objeto Task da aplicação
                //
                // A tela não cria TaskEntity,
                // porque ela não sabe nada sobre banco.
                taskViewModel.addTask(
                    Task(
                        // O Room vai gerar esse valor
                        id = 0,
                        title = title,
                        completed = false
                    )
                )
                // Avisamos a navegação que terminou
                // O Navigation decide para onde voltar
                onTaskSaved()
            }
        ) {
            Text(
                text = "Salvar"
            )
        }
    }

}