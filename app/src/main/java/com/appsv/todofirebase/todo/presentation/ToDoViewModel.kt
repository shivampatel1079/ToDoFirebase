package com.appsv.todofirebase.todo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsv.todofirebase.todo.data.repository.ToDoRepositoryImpl
import com.appsv.todofirebase.todo.domain.model.ToDoUI
import com.appsv.todofirebase.todo.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ToDoViewModel : ViewModel() {
    private val toDoRepository : ToDoRepository = ToDoRepositoryImpl()

    private val _state = MutableStateFlow(ToDoState())
    val state = _state.asStateFlow()

    fun onEvent(events: ToDoEvents){
        when(events){
            is ToDoEvents.SaveToDo -> {
                saveToDo(events.toDoUI)
            }
        }
    }

    private fun saveToDo(toDoUI: ToDoUI){

        viewModelScope.launch {
            toDoRepository.saveToDo(toDoUI)
        }
    }
}