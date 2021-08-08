package com.example.TODOList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.task_sample.*


class TaskAdapter(private val listner: (Long)->Unit) :
   ListAdapter<Task, TaskAdapter.TaskViewHolder>(diffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_sample, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }


    inner class TaskViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        init {
            itemView.setOnClickListener {
                listner.invoke(getItem(adapterPosition).id)
            }

        }

        fun bindView(task: Task) {
            with(task)
            {
                task_title.text = title
                task_detail.text = detail
                when (priority) {
                Priority.LOW.ordinal->
                        priority_color.setBackgroundResource(R.color.low_priority)
                    Priority.MEDIUM.ordinal->
                        priority_color.setBackgroundResource(R.color.medium_priority)
                    Priority.HIGH.ordinal->
                        priority_color.setBackgroundResource(R.color.high_priority)

                }
            }
        }
    }


    class diffUtilCallBack : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

    }
}