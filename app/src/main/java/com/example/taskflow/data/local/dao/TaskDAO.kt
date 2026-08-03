package com.example.taskflow.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.taskflow.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getTasks(): Flow<List<TaskEntity>>


    @Insert
    suspend fun insertTask(task: TaskEntity)


    @Update
    suspend fun updateTask(task: TaskEntity)


    @Delete
    suspend fun deleteTask(task: TaskEntity)
}