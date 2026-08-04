package com.example.taskflow.di

import com.example.taskflow.data.local.dao.TaskDao
import com.example.taskflow.data.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module responsável por fornecer
 * todas as dependências relacionadas
 * ao Repository.
 *
 * O Repository é a camada que faz
 * a ponte entre a ViewModel e o banco.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Sempre que alguém precisar de um
     * TaskRepository, o Hilt executará
     * este método.
     *
     * O TaskDao será fornecido automaticamente
     * pelo DatabaseModule.
     */
    @Provides
    @Singleton
    fun provideTaskRepository(
        taskDao: TaskDao
    ): TaskRepository {

        return TaskRepository(taskDao)

    }

}