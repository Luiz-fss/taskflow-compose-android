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


    // Busca todas as tarefas do banco
    //
    // Usamos Flow porque queremos observar mudanças:
    // inseriu uma tarefa?
    // atualizou?
    // deletou?
    //
    // O Room avisa automaticamente.
    @Query("SELECT * FROM tasks")
    fun getTasks(): Flow<List<TaskEntity>>



    // Busca uma tarefa específica pelo ID
    //
    // Retorna uma TaskEntity porque estamos no
    // lado do banco de dados.
    //
    // O ? significa que pode não existir uma tarefa
    // com esse ID.
    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: Int): TaskEntity?



    // Insere uma nova tarefa no banco
    @Insert
    suspend fun insertTask(task: TaskEntity)



    // Atualiza uma tarefa existente
    @Update
    suspend fun updateTask(task: TaskEntity)



    // Remove uma tarefa do banco
    @Delete
    suspend fun deleteTask(task: TaskEntity)

}