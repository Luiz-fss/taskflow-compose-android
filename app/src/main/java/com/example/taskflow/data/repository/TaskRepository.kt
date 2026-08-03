package com.example.taskflow.data.repository

import com.example.taskflow.data.local.dao.TaskDao
import com.example.taskflow.data.mapper.toTask
import com.example.taskflow.data.mapper.toTaskEntity
import com.example.taskflow.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TaskRepository(
    private val taskDao: TaskDao
) {


    val tasks: Flow<List<Task>> =
        taskDao.getTasks()
            .map { tasksEntity ->

                tasksEntity.map {
                    it.toTask()
                }

            }


    suspend fun addTask(task: Task) {

        taskDao.insertTask(
            task.toTaskEntity()
        )

    }


    suspend fun updateTask(task: Task) {

        taskDao.updateTask(
            task.toTaskEntity()
        )

    }


    suspend fun deleteTask(task: Task) {

        taskDao.deleteTask(
            task.toTaskEntity()
        )

    }
}