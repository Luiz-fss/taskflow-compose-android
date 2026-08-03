package com.example.taskflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskflow.data.repository.TaskRepository
import com.example.taskflow.model.Task
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class TaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {
    val tasks = repository.tasks
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
        }
    }
    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
    // Busca uma tarefa pelo ID
    //
    // A tela não acessa o Repository diretamente.
    // Ela pede para a ViewModel.
    //
    // A ViewModel chama o Repository dentro
    // de uma coroutine porque a operação é suspend.
    fun getTaskById(
        id: Int,
        onResult: (Task?) -> Unit
    ) {
        viewModelScope.launch {
            val task = repository.getTaskById(id)
            onResult(task)
        }
    }
}