package com.appsv.todofirebase.core.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.appsv.todofirebase.R
import com.appsv.todofirebase.core.utils.Priority

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ToDoDialogPreview(){
    ToDoDialog(
        onDismiss = {},
        onAddToDo = {_,_,_,->},
        onDeleteToDo = {},
        onUpdateToDo = {_,_,_,->}
    )
}

@Composable
fun ToDoDialog(
    isEditMode: Boolean = false,
    onDismiss: () -> Unit,
    onAddToDo : (String,String,Priority) -> Unit,

    onUpdateToDo : (String, String, Priority) -> Unit,
    onDeleteToDo : () -> Unit,

    existingTitle: String = "",
    existingDescription : String = "",
    existingPriority : Priority = Priority.LOW

)
//we show dialog 2 times when add, again fro edit and save
{
   Dialog(
       onDismissRequest = {onDismiss()},
       properties = DialogProperties(usePlatformDefaultWidth = false)
   ) {
       Surface(
           shape = MaterialTheme.shapes.medium,
           modifier = Modifier.padding(16.dp)
       ){

           var currentTitle by
           rememberSaveable { mutableStateOf(if (isEditMode) existingTitle else "" ) }
           var currentDescription by
           rememberSaveable { mutableStateOf(if (isEditMode) existingDescription else "" ) }
           var currentPriority by
           rememberSaveable { mutableStateOf(if (isEditMode) existingPriority else Priority.LOW) }
           var isTitleEmpty by rememberSaveable { mutableStateOf(false) }

           var enable by rememberSaveable {
               if (isEditMode) mutableStateOf(false) else mutableStateOf(true)
           }

           var confirmDeletingToDo by rememberSaveable {
               mutableStateOf(false)
           }

           val focusRequester = remember { FocusRequester() }

           val focusManager = LocalFocusManager.current

           //when add
           LaunchedEffect(Unit) {
               focusRequester.requestFocus()
           }

           //when update/delete
           LaunchedEffect(key1 = enable) {
               focusRequester.requestFocus()
           }

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
                       verticalAlignment = Alignment.CenterVertically,
                       horizontalArrangement = Arrangement.spacedBy(8.dp)
                   ){
                       if (isEditMode){
                           Box(
                               modifier = Modifier.background(
                                   Color.Cyan.copy(alpha = 0.2f),
                                   shape = MaterialTheme.shapes.extraLarge
                               )
                           ){
                               IconButton(
                                   onClick = {
                                       enable = true
                                   }
                               ) {
                                   Icon(
                                       imageVector = Icons.Default.Edit,
                                       contentDescription = "Edit ToDo",
                                       tint = Color.Cyan
                                   )
                               }
                           }

                           Box(
                               modifier = Modifier.background(
                                   Color.Red.copy(alpha = 0.2f),
                                   shape = MaterialTheme.shapes.extraLarge
                               )
                           ){
                               IconButton(
                                   onClick = {
                                       confirmDeletingToDo = true
                                   }
                               ) {
                                   Icon(
                                       imageVector = Icons.Default.Delete,
                                       contentDescription = "Delete ToDo",
                                       tint = Color.Red
                                   )
                               }
                           }
                       }
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
                       currentPriority = Priority.LOW // Lambda call using onClick
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
               Spacer(modifier = Modifier.height(10.dp))

               CustomizedTextField(
                   modifier = Modifier.focusRequester(focusRequester),
                   text = currentTitle,
                   label = "ToDo Title*",
                   onValueChange = {
                       currentTitle = it
                   },
                   enabled = enable,
                   supportingText = if (isTitleEmpty){
                       "Please enter title least!"
                   } else "",
                   keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next ),
                   keyboardActions = KeyboardActions(
                       onNext = {
                           focusManager.moveFocus(FocusDirection.Down)
                       }
                   )
               )

               Spacer(modifier = Modifier.height(8.dp))

               CustomizedTextField(
                   modifier = Modifier.fillMaxWidth(),
                   text = currentDescription,
                   label = "ToDo Description",
                   onValueChange = {
                       currentDescription = it
                   },
                   enabled = enable,
               )
               Spacer(modifier = Modifier.height(16.dp))

               //buttons for cancel, update, delete
               Row (
                   horizontalArrangement = Arrangement.End,
                   modifier = Modifier.fillMaxWidth()
               ){
                   Button(
                       onClick = {onDismiss()},
                       modifier = Modifier.padding(8.dp),
                       colors = ButtonDefaults.buttonColors(
                           containerColor = Color.Red.copy(alpha = 0.6f),
                           contentColor = Color.White
                       )
                   ) {
                       Text("Cancel", fontWeight = FontWeight.Bold)
                   }
                   if (isEditMode){
                       Button(
                           onClick = {
                               if(currentTitle.isNotEmpty()){
                                   onAddToDo(
                                       currentTitle,
                                       currentDescription,
                                       currentPriority
                                   )
                               } else {
                                   isTitleEmpty = true
                               }
                           },
                           enabled = currentTitle != existingTitle
                                   || currentDescription != existingDescription ||
                                   existingPriority != currentPriority,
                           colors = ButtonDefaults.buttonColors(
                               containerColor = Color.Green.copy(0.6f),
                               contentColor = Color.White,
                               disabledContentColor = Color.LightGray
                           )
                       ) {
                           Text("Add ToDo", fontWeight = FontWeight.Bold)
                       }
                   }
                   else{
                       Button(
                           onClick = {
                               if(currentTitle.isNotEmpty()){
                                   onAddToDo(
                                       currentTitle,
                                       currentDescription,
                                       currentPriority
                                   )
                               } else {
                                   isTitleEmpty = true
                               }
                           },
                           colors = ButtonDefaults.buttonColors(
                               containerColor = Color.Green.copy(0.6f),
                               contentColor = Color.White
                           )
                       ) {
                           Text("Add ToDo", fontWeight = FontWeight.Bold)
                       }
                   }
               }

               if(confirmDeletingToDo){
                   SimpleAlertDialog(
                       title = "Deleting ToDo?",
                       text = "Are you sure want to delete this ToDo?",
                       onConfirm = {
                           onDeleteToDo()
                           confirmDeletingToDo = false
                       },
                       onDismiss = {
                           confirmDeletingToDo = false
                       }
                   ){

                   }
               }
           }
       }
   }
}