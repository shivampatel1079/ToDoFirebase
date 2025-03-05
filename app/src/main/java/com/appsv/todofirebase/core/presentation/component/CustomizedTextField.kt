package com.appsv.todofirebase.core.presentation.component

import android.widget.NumberPicker.OnValueChangeListener
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.appsv.todofirebase.R

@Composable
fun CustomizedTextField(
    modifier: Modifier = Modifier,
    text:String,
    label : String,
    onValueChange : (String) -> Unit,
    enabled : Boolean,
    supportingText : String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default

) {
    OutlinedTextField(
        modifier = Modifier.fillMaxSize(),
        value = text,
        colors = OutlinedTextFieldDefaults.colors().copy(
            focusedTextColor = colorResource(R.color.light1_blue),
            unfocusedTextColor = colorResource(R.color.light1_blue),
            focusedContainerColor = colorResource(R.color.dark_blue),
            unfocusedContainerColor = colorResource(R.color.dark_blue),
            focusedLeadingIconColor = colorResource(R.color.light1_blue),
            unfocusedLeadingIconColor = colorResource(R.color.light1_blue),
            focusedIndicatorColor = colorResource(R.color.light1_blue),
            unfocusedIndicatorColor = colorResource(R.color.light1_blue),
            focusedLabelColor = colorResource(R.color.light1_blue),
            unfocusedLabelColor = colorResource(R.color.light1_blue),
            cursorColor = colorResource(R.color.light1_blue),
            disabledTextColor = Color.LightGray,
            disabledLabelColor = Color.LightGray,
            disabledIndicatorColor = Color.LightGray,
        ),
        onValueChange = {
            onValueChange(it)
        },
        enabled = enabled,
        label = { Text(text=label) },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        supportingText = {
            Text(text=supportingText, color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    )
}