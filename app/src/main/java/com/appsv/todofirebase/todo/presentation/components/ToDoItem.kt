package com.appsv.todofirebase.todo.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appsv.todofirebase.core.utils.Priority
import com.appsv.todofirebase.todo.domain.model.ToDoUI


@Composable
fun ToDoItemPreview(modifier: Modifier = Modifier) {
    TodoItem(
        toDoUI = ToDoUI(
            id = "",
            title = "Record Video for AI",
            description = "I have to do this, It is IMP",
            priority = Priority.HIGH,
            dateAdded = "18 Oct, 5:21 am, 2024"
        )
    )
}

@Composable
fun TodoItem(modifier: Modifier = Modifier,
             toDoUI: ToDoUI
) {
    val containerColor = when(toDoUI.priority){
        Priority.LOW -> Color.Green
        Priority.MEDIUM -> Color.Yellow
        Priority.HIGH -> Color.Red
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            //title
            Text(text = toDoUI.title,
                 fontSize = 26.sp,
                 fontWeight = FontWeight.Bold,
                 color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
                )
            if (toDoUI.description != ""){
                Spacer(modifier = Modifier.height(10.dp))
                //description
                Text(text = toDoUI.description,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            //date
            Text(modifier = Modifier.fillMaxSize(),
                text = toDoUI.dateAdded,
                fontSize = 15.sp,
                color = Color.White,
                textAlign = TextAlign.End
            )
        }
    }
}