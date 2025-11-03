package com.example.signalmatch_frontend.ui.components.etc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.example.signalmatch_frontend.R

@Composable
fun Logo(size: Dp) {
    Image(
        painter = painterResource(R.drawable.ic_logo),
        contentDescription = "앱 로고",
        modifier = Modifier.size(size)
    )
}