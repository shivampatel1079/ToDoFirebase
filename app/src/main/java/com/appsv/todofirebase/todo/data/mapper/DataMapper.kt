package com.appsv.todofirebase.todo.data.mapper

import com.appsv.todofirebase.todo.data.ToDoDTO
import com.appsv.todofirebase.todo.domain.model.ToDoUI
import java.util.UUID

fun ToDoUI.toToDoDTO() : ToDoDTO{
    return ToDoDTO(
        id = UUID.randomUUID().toString(),
        title = title,
        description = description,
        priority = priority,
        dateAdded = System.currentTimeMillis()
    )
}