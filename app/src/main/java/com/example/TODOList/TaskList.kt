package com.example.TODOList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.fragment_task_list.*


class TaskList : Fragment(), LifecycleObserver {
private lateinit var taskListModel:TaskListModel
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        taskListModel= ViewModelProvider(this).get(TaskListModel::class.java)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        with(tasks_list) {
            layoutManager = LinearLayoutManager(activity)
            adapter = TaskAdapter {
                findNavController().navigate(
                    TaskListDirections.actionTaskListToTaskDetail(
                        it
                    )
                )
                setHasFixedSize(true)

            }
        }
            taskListModel.tasks.observe(viewLifecycleOwner, {
               (tasks_list.adapter as TaskAdapter).submitList(it)
             })


            addTask.setOnClickListener {

            findNavController().navigate(TaskListDirections.actionTaskListToTaskDetail(0L))

            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sortmenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.priority_sort -> {
taskListModel.changeSortType(sortOrder.priority)
                item.isChecked = true
                true
            }
            R.id.title_sort -> {
                taskListModel.changeSortType(sortOrder.title)
                item.isChecked = true
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}






