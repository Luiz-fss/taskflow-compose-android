package com.example.taskflow.di

import android.content.Context
import androidx.room.Room
import com.example.taskflow.data.local.TaskDatabase
import com.example.taskflow.data.repository.TaskRepository


class AppContainer(
    context: Context
) {

    // Cria a instância do banco Room
    // Esse objeto representa nosso SQLite
    private val database =
        Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "task_database"
        ).build()


    // Pegamos o DAO através do banco
    // O DAO é quem conversa com as tabelas
    private val taskDao =
        database.taskDao()


    // Criamos o Repository passando o DAO
    // A ViewModel vai receber esse Repository
    val taskRepository =
        TaskRepository(taskDao)

}