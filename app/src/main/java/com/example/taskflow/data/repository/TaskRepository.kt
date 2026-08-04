package com.example.taskflow.data.repository

import com.example.taskflow.data.local.dao.TaskDao
import com.example.taskflow.data.mapper.toTask
import com.example.taskflow.data.mapper.toTaskEntity
import com.example.taskflow.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Repository responsável por intermediar
 * a comunicação entre a ViewModel e o DAO.
 *
 * O Repository não sabe criar um TaskDao.
 *
 * Ele apenas declara que precisa dele.
 *
 * O Hilt será responsável por fornecer
 * essa dependência automaticamente.
 */
class TaskRepository @Inject constructor(

    /**
     * DAO injetado automaticamente
     * pelo Hilt.
     */
    private val taskDao: TaskDao

) {

    /**
     * Lista observável de tarefas.
     *
     * O DAO retorna TaskEntity.
     *
     * Aqui convertemos para o modelo
     * utilizado pela aplicação.
     */
    val tasks: Flow<List<Task>> =
        taskDao.getTasks()
            .map { tasksEntity ->

                tasksEntity.map {
                    it.toTask()
                }

            }

    /**
     * Adiciona uma nova tarefa.
     */
    suspend fun addTask(task: Task) {

        taskDao.insertTask(
            task.toTaskEntity()
        )

    }

    /**
     * Atualiza uma tarefa existente.
     */
    suspend fun updateTask(task: Task) {

        taskDao.updateTask(
            task.toTaskEntity()
        )

    }

    /**
     * Remove uma tarefa.
     */
    suspend fun deleteTask(task: Task) {

        taskDao.deleteTask(
            task.toTaskEntity()
        )

    }

    /**
     * Busca uma tarefa específica pelo ID.
     *
     * O DAO retorna uma TaskEntity.
     *
     * O Repository converte para
     * o modelo utilizado pela aplicação.
     */
    suspend fun getTaskById(
        id: Int
    ): Task? {

        return taskDao
            .getTaskById(id)
            ?.toTask()

    }

}