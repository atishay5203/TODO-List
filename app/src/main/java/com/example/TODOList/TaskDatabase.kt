package com.example.TODOList

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


    @Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
    public abstract class TaskDatabase : RoomDatabase() {

        abstract fun taskListDao(): TaskListDao
        abstract fun taskDetailDao():TaskDetailDao

        companion object {
            // Singleton prevents multiple instances of database opening at the
            // same time.
            @Volatile
            private var INSTANCE: TaskDatabase? = null

            fun getDatabase(context: Context): TaskDatabase {
                // if the INSTANCE is not null, then return it,
                // if it is, then create the database
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                       TaskDatabase::class.java,
                        "task_database"
                    ).build()
                    INSTANCE = instance
                    // return instance
                    instance 
                }
            }
        }
    }
