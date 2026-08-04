package com.example.taskflow.di

import android.content.Context
import androidx.room.Room
import com.example.taskflow.data.local.TaskDatabase
import com.example.taskflow.data.local.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module do Hilt responsável
 * por fornecer tudo relacionado
 * ao Room Database.
 *
 * Em vez de criarmos manualmente
 * o banco dentro do AppContainer,
 * agora o próprio Hilt sabe
 * como criá-lo.
 */
@Module

// Diz ao Hilt que este módulo
// ficará disponível durante
// toda a vida da aplicação.
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Cria uma única instância
     * do banco Room.
     *
     * @Singleton garante que
     * somente um banco será criado.
     */
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): TaskDatabase {

        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "task_database"
        ).build()

    }

    /**
     * Fornece o DAO.
     *
     * O Hilt já sabe criar
     * o TaskDatabase.
     *
     * Basta recebê-lo como parâmetro.
     */
    @Provides
    @Singleton
    fun provideTaskDao(
        database: TaskDatabase
    ): TaskDao {

        return database.taskDao()

    }

}