package com.example.TODOList

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class TaskDetailModel(context:Application):AndroidViewModel(context) {
    private val repository= TaskDetailRepository(context)
    private val _taskId= MutableLiveData<Long>(0)
    val taskId:LiveData<Long>
    get()=_taskId
    fun setTaskId(id:Long)
    {
        with(_taskId)
        {
            if(this.value!=id)
            {
                this.value=id
            }
        }
    }
    val task:LiveData<Task> = Transformations.switchMap(_taskId)
    {
        repository.getTask(it)
    }
    fun saveTask(task:Task)
    {
        viewModelScope.launch {
            if(_taskId.value==0L)
            {
                repository.insertTask(task)
            }
            else
                repository.updateTask(task)

        }

    }
    fun deleteTask()
    {
        viewModelScope.launch {
task.value?.run {
    repository.deleteTask(this)
}
        }
    }


}