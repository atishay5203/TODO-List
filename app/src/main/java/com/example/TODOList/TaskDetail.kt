package com.example.TODOList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_task_detail.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class TaskDetail : Fragment() {

private lateinit var viewModal:TaskDetailModel
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModal= ViewModelProvider(this).get(TaskDetailModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val priorityLevels= mutableListOf<String>()
        Priority.values().forEach {
            priorityLevels.add(it.name)
        }
val adapter= ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item,priorityLevels)
        priority_select.adapter=adapter
        priority_select?.onItemSelectedListener= object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateColor(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


val id= TaskDetailArgs.fromBundle(requireArguments()).id
            viewModal.setTaskId(id)
        viewModal.task.observe(viewLifecycleOwner, Observer {
            it?.let { setData(it) }
        })
        save.setOnClickListener {
            saveTask()
        }

        delete.setOnClickListener {
            deleteTask()
        }

        }




    private fun updateColor(position: Int) {
        when (position) {
            Priority.LOW.ordinal ->
                priority_status.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.low_priority))
            Priority.MEDIUM.ordinal ->
                priority_status.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.medium_priority))
            Priority.HIGH.ordinal ->
                priority_status.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.high_priority))
        }




    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }






private fun setData(task: Task){
    updateColor(task.priority)
    add_title.setText(task.title)
   add_detail.setText(task.detail)
    if(task.status == status.open.ordinal){
        status_open.isChecked = true
    } else{
        status_closed.isChecked = true
    }
   priority_select.setSelection(task.priority)

}

private fun saveTask(){
    val title = add_title.text.toString()
    val detail = add_detail.text.toString()
    val priority = priority_select.selectedItemPosition

    val selectedStatusButton =  status_group.findViewById<RadioButton>(status_group.checkedRadioButtonId)
    var statuse = status.open.ordinal
    if(selectedStatusButton.text == status.closed.name){
        statuse = status.closed.ordinal
    }
    val task = Task(viewModal.taskId.value!!, title, detail, priority, statuse)
    viewModal.saveTask(task)

    requireActivity().onBackPressed()
}

private fun deleteTask(){
    viewModal.deleteTask()

    requireActivity().onBackPressed()
}


}

