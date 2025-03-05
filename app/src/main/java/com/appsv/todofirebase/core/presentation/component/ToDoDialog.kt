package com.appsv.todofirebase.core.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.appsv.todofirebase.R
import com.appsv.todofirebase.core.utils.Priority

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ToDoDialog(
    isEditing: Boolean = true,
    onDismiss: () -> Unit,
    onAddToDo : (String,String,Priority) -> Unit,

    //
    //
    //
    //
    //

    )
//we show dialog 2 times when add, again fro edit and save
{
   Dialog(
       onDismissRequest = {},
       properties = DialogProperties(usePlatformDefaultWidth = false)
   ) {
       Surface(
           shape = MaterialTheme.shapes.medium,
           modifier = Modifier.padding(16.dp)
       ){
           var currentPriority by
           rememberSaveable { mutableStateOf(if (isEditMode) existingPriority else Priority.LOW) }

           Column(
               modifier = Modifier
                   .verticalScroll(rememberScrollState()) //for landscape mode
                   .background(colorResource(R.color.dark_blue))
                   .border(2.dp, colorResource(R.color.light1_blue), RoundedCornerShape(15.dp))
                   .padding(16.dp)
           ) {
               //Row Contains title and icons for both mode
               Row(
                   modifier = Modifier.fillMaxSize(),
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.SpaceBetween
               ) {
                   Text(
                       text = if (isEditMode) "Edit/Delete ToDo" else "Add ToDo",
                       style = MaterialTheme.typography.titleMedium,
                       color = Color.White,
                       fontSize = 18.sp
                   )
                   // icons for update and Delete
                   Row (

                   ){

                   }
               }
               Spacer(modifier = Modifier.height(10.dp))

               //Priority
               Row(
                   modifier = Modifier
                       .fillMaxSize()
                       .padding(start = 10.dp),
                   horizontalArrangement = Arrangement.spacedBy(10.dp)

               ) {
                   IconWithCircleBackground(
                       selected = currentPriority == Priority.LOW,
                       priority = Priority.LOW
                   ) {
                       currentPriority = Priority.LOW
                   }
                   IconWithCircleBackground(
                       selected = currentPriority == Priority.MEDIUM,
                       priority = Priority.MEDIUM
                   ) {
                       currentPriority = Priority.MEDIUM
                   }
                   IconWithCircleBackground(
                       selected = currentPriority == Priority.HIGH,
                       priority = Priority.HIGH
                   ) {
                       currentPriority = Priority.HIGH
                   }
               }
           }
       }
   }
}