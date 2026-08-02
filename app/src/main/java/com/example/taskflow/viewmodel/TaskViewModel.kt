package com.example.taskflow.viewmodel

import androidx.lifecycle.ViewModel
import com.example.taskflow.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel: ViewModel() {
    private val _tasks = MutableStateFlow(
        listOf(
            Task(
                1,
                "Estudar Compose",
                false
            ),
            Task(
                2,
                "Criar TaskFlow",
                true
            )
        )
    )

    val tasks = _tasks.asStateFlow()

    fun toggleTask(id: Int) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == id) {
                task.copy(
                    completed = !task.completed
                )
            } else {
                task
            }
        }
    }
}