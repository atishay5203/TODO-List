package com.example.TODOList

import android.app.Application
import androidx.lifecycle.LiveData

class TaskListRepository(context: Application) {
    private  val taskListDao= TaskDatabase.getDatabase(context).taskListDao()
    fun getTasks(sort: sortOrder = sortOrder.priority): LiveData<List<Task>> {
        if(sort==sortOrder.priority)
        {
            return taskListDao.getTasksByPriority(status.open.ordinal)
        }
        else
            return taskListDao.getTasksByTitle(status.open.ordinal)

    }



}