package com.appsv.todofirebase.todo.domain.repository

import com.appsv.todofirebase.todo.domain.model.ToDoUI

interface ToDoRepository {

    suspend fun saveToDo(toDoUI: ToDoUI) //ham here toDoUI ko nah toDoDTO ko save
    suspend fun getToDO() : List<ToDoUI> //here we get data in form of DTO then convert to ToDoUI

}