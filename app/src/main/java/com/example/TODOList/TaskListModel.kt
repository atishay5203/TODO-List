package com.example.TODOList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

class TaskListModel(context:Application):AndroidViewModel(context) {
    private val repository= TaskListRepository(context)
    private val _sortType = MutableLiveData<sortOrder>(sortOrder.priority)
    val sortType :LiveData<sortOrder>
    get() = _sortType
    val tasks :LiveData<List<Task>> = Transformations.switchMap(sortType)
    {
        repository.getTasks(sortType.value!!)
    }

    fun changeSortType(sort:sortOrder)
    {
     _sortType.value=sort
    }
}