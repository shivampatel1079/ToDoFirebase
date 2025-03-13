package com.appsv.todofirebase.todo.presentation

import com.appsv.todofirebase.todo.domain.model.ToDoUI

sealed class ToDoEvents {
    data class SaveToDo(val toDoUI: ToDoUI) : ToDoEvents()

    data class UpdateToDo(val toDoUI: ToDoUI) : ToDoEvents()

    data class DeleteToDo(val id : String) : ToDoEvents()

}