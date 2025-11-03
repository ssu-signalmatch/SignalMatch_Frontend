package com.example.signalmatch_frontend.ui.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NavigationText(
    question: String,
    actionText: String,
    onActionClick: () -> Unit
) {
    Row {
        Text(text = question,
            fontSize = 16.sp,
            color = Color(0xFF848484)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = actionText,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFFADF1EB),
            modifier = Modifier.clickable { onActionClick() }
        )
    }
}