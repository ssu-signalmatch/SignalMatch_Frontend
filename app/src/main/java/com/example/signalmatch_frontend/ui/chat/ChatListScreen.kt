package com.example.signalmatch_frontend.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.signalmatch_frontend.ui.components.etc.Logo


@Preview
@Composable
fun ChatListScreenPreview () {
    val navController: NavHostController = rememberNavController()

    ChatListScreen(
        navController = navController
    )
}

@Composable
fun ChatListScreen (navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Logo(105.dp)
        ChatListContainer(
            chatItems = arrayListOf(
                ChatListItem(name = "Lorem Ipsum", preview="Lorem Ipsum", time = "오후 55:55", badge = "999"),
                ChatListItem(name = "Lorem Ipsum", preview="Lorem Ipsum", time = "오후 55:55", badge = "999")
            )
        )
    }
}