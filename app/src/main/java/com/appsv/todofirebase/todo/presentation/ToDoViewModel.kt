package com.appsv.todofirebase.todo.presentation

import android.util.Log
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

    init {
        getToDo()
    }

    fun onEvent(events: ToDoEvents){
        when(events){
            is ToDoEvents.SaveToDo -> {
                saveToDo(events.toDoUI)
            }

            is ToDoEvents.DeleteToDo -> {
                deleteToDo(events.id)
            }
            is ToDoEvents.UpdateToDo -> {
                updateToDo(events.toDoUI)
            }
        }
    }

    private fun saveToDo(toDoUI: ToDoUI){

        viewModelScope.launch {
            toDoRepository.saveToDo(toDoUI)
        }
    }

    private fun getToDo(){
        viewModelScope.launch {

            //_state.value = state.value.copy(isLoading = true)

            toDoRepository.getToDO().collect{toDoList ->

                _state.value = state.value.copy(
                    toDoList = toDoList,
                    isLoading = false
                )
            }

        }
    }

    private fun updateToDo(toDoUI: ToDoUI){
        viewModelScope.launch { //we launch bec. in repo its suspend fun.
            toDoRepository.updateToDo(toDoUI)
        }
    }

    private fun deleteToDo(id: String){
        viewModelScope.launch {
            toDoRepository.deleteToDo(id)
        }
    }
}