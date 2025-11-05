package com.example.signalmatch_frontend.ui.components.text

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun OutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text( label, fontSize = 16.sp) },
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color(0xFFD9D9D9),
            unfocusedIndicatorColor = Color(0xFFD9D9D9),
            focusedLabelColor = Color(0xFF848484),
            unfocusedLabelColor = Color(0xFF848484)
        ),
        modifier = Modifier
            .height(71.dp)
            .width(357.dp)
    )
}