package com.example.taskflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskflow.data.repository.TaskRepository
import com.example.taskflow.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * ViewModel responsável por controlar
 * todas as regras relacionadas às tarefas.
 *
 * A ViewModel NÃO sabe criar um Repository.
 *
 * Ela apenas diz:
 *
 * "Eu preciso de um TaskRepository."
 *
 * O Hilt será responsável por fornecer
 * essa dependência automaticamente.
 */
@HiltViewModel
class TaskViewModel @Inject constructor(
    /**
     * Repository injetado automaticamente
     * pelo Hilt.
     *
     * Fluxo:
     *
     * Hilt
     * ↓
     * RepositoryModule
     * ↓
     * TaskRepository
     */
    private val repository: TaskRepository

) : ViewModel() {

    /**
     * Lista observável de tarefas.
     *
     * O Repository fornece um Flow.
     *
     * stateIn() transforma esse Flow
     * em um StateFlow que pode ser
     * observado pelo Compose.
     */
    val tasks = repository.tasks
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    /**
     * Adiciona uma nova tarefa.
     */
    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
        }
    }

    /**
     * Atualiza uma tarefa existente.
     */
    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    /**
     * Remove uma tarefa.
     */
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
    /**
     * Busca uma tarefa pelo ID.
     *
     * A tela nunca acessa
     * o Repository diretamente.
     *
     * Toda comunicação passa
     * pela ViewModel.
     */
    fun getTaskById(
        id: Int,
        onResult: (Task?) -> Unit
    ) {
        viewModelScope.launch {
            val task =
                repository.getTaskById(id)
            onResult(task)
        }
    }
}