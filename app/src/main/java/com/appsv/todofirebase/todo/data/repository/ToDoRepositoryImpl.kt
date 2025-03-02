package com.appsv.todofirebase.todo.data.repository

import com.appsv.todofirebase.todo.data.mapper.toToDoDTO
import com.appsv.todofirebase.todo.domain.model.ToDoUI
import com.appsv.todofirebase.todo.domain.repository.ToDoRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class ToDoRepositoryImpl : ToDoRepository {

    val firebase = FirebaseDatabase.getInstance()
    val toDoRef = firebase.getReference("ToDoItems")

    override suspend fun saveToDo(toDoUI: ToDoUI) {
        val toDoDTO = toDoUI.toToDoDTO()

        try {
            toDoRef.child(toDoDTO.id).setValue(toDoDTO).await()
        }
        catch (e: Exception){}
    }

    override suspend fun getToDO(): List<ToDoUI> {
        TODO("Not yet implemented")
    }

}