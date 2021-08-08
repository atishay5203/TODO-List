package com.example.TODOList

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDetailDao {
    @Query("SELECT * FROM tasks WHERE `id`=:id")
    fun getTask(id:Long):LiveData<Task>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task:Task):Long
    @Update
    suspend fun updateTask(task: Task)
    @Delete
  suspend  fun deleteTask(task: Task)
  //we use suspend as room doesnt allow the queries to run in the background if they do not return Livedata and if we write suspend
  //it allows to do so
}