package com.example.TODOList

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class status
{
open,closed
}
enum class Priority
{
    LOW,MEDIUM,HIGH
}
enum class sortOrder
{
    priority,title
}
@Entity(tableName = "tasks")
data class Task(@PrimaryKey(autoGenerate = true)val id:Long,val title:String,val detail:String,val priority:Int,val status:Int)

{

}
