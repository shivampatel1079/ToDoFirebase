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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsv.todofirebase.R
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
                items(state.toDoList, key = {it.id}){
                    TodoItem(toDoUI = it)
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
                scope.launch {
                    events(ToDoEvents.SaveToDo(
                        ToDoUI(
                            id = "1",
                            title = "Record Video for AI",
                            description = "I have to do this, It is IMP",
                            priority = Priority.HIGH,
                            dateAdded = "18 Oct, 5:21 am, 2024"
                        )
                    ))
                }
            }
        ) {
            Icon(imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White
            )
        }
    }
}

val toDoUIList = listOf(
    ToDoUI(
        id = "1",
        title = "Record Video for AI",
        description = "I have to do this, It is IMP",
        priority = Priority.HIGH,
        dateAdded = "18 Oct, 5:21 am, 2024"
    ),
    ToDoUI(
        id = "2",
        title = "Complete Android Assignment",
        description = "Finish Jetpack Compose UI implementation",
        priority = Priority.MEDIUM,
        dateAdded = "19 Oct, 10:30 am, 2024"
    ),
    ToDoUI(
        id = "3",
        title = "Prepare for Coding Interview",
        description = "",
        priority = Priority.HIGH,
        dateAdded = "20 Oct, 7:00 pm, 2024"
    ),
    ToDoUI(
        id = "4",
        title = "Read AI Research Paper",
        description = "Study new trends in deep learning",
        priority = Priority.MEDIUM,
        dateAdded = "21 Oct, 8:45 am, 2024"
    ),
    ToDoUI(
        id = "5",
        title = "Workout Session",
        description = "Go to the gym and do strength training",
        priority = Priority.LOW,
        dateAdded = "22 Oct, 6:00 am, 2024"
    ),
    ToDoUI(
        id = "6",
        title = "Fix Chat App Bugs",
        description = "Resolve reported issues in the chat app",
        priority = Priority.HIGH,
        dateAdded = "23 Oct, 11:15 am, 2024"
    ),
    ToDoUI(
        id = "7",
        title = "Write Blog on Jetpack Compose",
        description = "Share learnings about state management",
        priority = Priority.MEDIUM,
        dateAdded = "24 Oct, 4:00 pm, 2024"
    ),
    ToDoUI(
        id = "8",
        title = "Meeting with Mentor",
        description = "Discuss career and project progress",
        priority = Priority.LOW,
        dateAdded = "25 Oct, 2:30 pm, 2024"
    ),
    ToDoUI(
        id = "9",
        title = "Submit Project Report",
        description = "Prepare and submit documentation",
        priority = Priority.HIGH,
        dateAdded = "26 Oct, 9:00 am, 2024"
    ),
    ToDoUI(
        id = "10",
        title = "Learn Kotlin Coroutines",
        description = "Deep dive into flow and suspend functions",
        priority = Priority.MEDIUM,
        dateAdded = "27 Oct, 7:45 pm, 2024"
    ),

    ToDoUI(
        id = "11",
        title = "Refactor Chat App Code",
        description = "Improve code structure and optimize performance",
        priority = Priority.HIGH,
        dateAdded = "28 Oct, 3:15 pm, 2024"
    ),
    ToDoUI(
        id = "12",
        title = "Watch Jetpack Compose Tutorial",
        description = "Learn about advanced animations in Compose",
        priority = Priority.MEDIUM,
        dateAdded = "29 Oct, 6:00 pm, 2024"
    ),
    ToDoUI(
        id = "13",
        title = "Buy Groceries",
        description = "Get fruits, vegetables, and snacks",
        priority = Priority.LOW,
        dateAdded = "30 Oct, 10:00 am, 2024"
    ),
    ToDoUI(
        id = "14",
        title = "Debug API Integration",
        description = "Fix authentication issues in API calls",
        priority = Priority.HIGH,
        dateAdded = "31 Oct, 1:45 pm, 2024"
    ),
    ToDoUI(
        id = "15",
        title = "Write Unit Tests",
        description = "Ensure all features in the chat app are well tested",
        priority = Priority.MEDIUM,
        dateAdded = "1 Nov, 9:30 am, 2024"
    )


)
