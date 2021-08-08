package com.example.TODOList

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskListDao {
    @Query("SELECT * FROM tasks WHERE status=:status ORDER BY priority DESC")
    fun getTasksByPriority(status:Int): LiveData<List<Task>>
    @Query("SELECT * FROM tasks WHERE status=:status ORDER BY title")
    fun getTasksByTitle(status:Int): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

//    @Query("DELETE FROM tasks")
//    suspend fun delete(task:Task)
}