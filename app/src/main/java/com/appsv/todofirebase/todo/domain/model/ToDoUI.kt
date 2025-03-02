package com.appsv.todofirebase.todo.domain.model

import com.appsv.todofirebase.core.utils.Priority


data class ToDoUI(
    val id : String,
    val title: String,
    val description : String = "",
    val priority: Priority = Priority.LOW,
    val dateAdded : String
)
