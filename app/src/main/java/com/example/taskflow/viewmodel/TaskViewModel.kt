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
}