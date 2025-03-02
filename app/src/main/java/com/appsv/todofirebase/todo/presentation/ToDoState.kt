package com.appsv.todofirebase.todo.presentation

import com.appsv.todofirebase.todo.domain.model.ToDoUI

data class ToDoState(
    val isLoading : Boolean = true,
    val toDoList : List<ToDoUI> = emptyList()
)