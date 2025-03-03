package com.appsv.todofirebase.todo.domain.mapper

import com.appsv.todofirebase.core.utils.formatTimestampToDateTime
import com.appsv.todofirebase.todo.data.ToDoDTO
import com.appsv.todofirebase.todo.domain.model.ToDoUI

fun ToDoDTO.toDoUI() : ToDoUI{
    return ToDoUI(
        id = id!!,
        title = title,
        description = description,
        priority = priority,
        dateAdded = formatTimestampToDateTime(dateAdded!!)
    )
}