package com.appsv.todofirebase.todo.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsv.todofirebase.R
import com.appsv.todofirebase.core.presentation.component.ToDoDialog
import com.appsv.todofirebase.core.utils.Priority
import com.appsv.todofirebase.todo.domain.model.ToDoUI
import com.appsv.todofirebase.todo.presentation.components.TodoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ToDoScreen(
    state: ToDoState,
    events: (ToDoEvents) -> Unit
) {
    val scope = rememberCoroutineScope()

    val showToDoDialog = rememberSaveable { mutableStateOf(false) }

    var isEditMode by rememberSaveable { mutableStateOf(false) }

    var selectedTitle by rememberSaveable { mutableStateOf("") }
    var selectedDescription by rememberSaveable { mutableStateOf("") }
    var selectedPriority by rememberSaveable { mutableStateOf(Priority.LOW) }
    var selectedId by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize().background(colorResource(R.color.dark_blue)),
        contentAlignment = Alignment.Center
    ){
        if (state.isLoading){
            CircularProgressIndicator()
        }
        else if (state.toDoList.isNotEmpty()){
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                items(state.toDoList, key = {it.id!!}){currentToDoUiItem->
                    TodoItem(toDoUI = currentToDoUiItem){
                        selectedId = currentToDoUiItem.id!!
                        selectedTitle = currentToDoUiItem.title
                        selectedDescription = currentToDoUiItem.description
                        selectedPriority = currentToDoUiItem.priority
                        isEditMode = true
                        showToDoDialog.value = true
                    }
                }
            }
        }
        else{
            Text(text = "No ToDos , Please add some ToDo!",
                color = colorResource(R.color.light1_blue)
            )

        }

        FloatingActionButton(
            modifier = Modifier.size(100.dp).padding(20.dp).align(Alignment.BottomEnd),
            containerColor = colorResource(R.color.medium_blue),
            onClick = {
                showToDoDialog.value = true
            }
        ) {
            Icon(imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White
            )
        }

        if (showToDoDialog.value){
            ToDoDialog(
                isEditMode = isEditMode,
                onDismiss = {
                    showToDoDialog.value = false
                },
                onAddToDo = {title,description,priority,->
                    scope.launch {
                        val toDoUI = ToDoUI(
                            //id,date = no need bec now provided initial value as null to it
                            title = title,
                            description = description,
                            priority = priority
                        )
                         events(ToDoEvents.SaveToDo(toDoUI))
                    }
                    showToDoDialog.value = false
                },
                onDeleteToDo = {
                    scope.launch {
                        events(ToDoEvents.DeleteToDo(selectedId))
                    }
                    showToDoDialog.value = false
                },
                onUpdateToDo = {title,description,priority,->
                    scope.launch {
                        val toDoUI = ToDoUI(
                            //id,date = no need bec now provided initial value as null to it
                            id = selectedId,
                            title = title,
                            description = description,
                            priority = priority
                        )
                        events(ToDoEvents.UpdateToDo(toDoUI))
                    }
                    showToDoDialog.value = false
                },
                existingPriority = selectedPriority,
                existingTitle = selectedTitle ,
                existingDescription = selectedDescription,
            )
        }
    }
}
