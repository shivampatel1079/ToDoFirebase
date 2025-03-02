package com.appsv.todofirebase.todo.data

import com.appsv.todofirebase.core.utils.Priority

data class ToDoDTO(
    val id : String,
    val title: String,
    val description : String = "",
    val priority: Priority = Priority.LOW,
    val dateAdded : Long
)
