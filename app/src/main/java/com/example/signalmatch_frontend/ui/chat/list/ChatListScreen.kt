package com.example.signalmatch_frontend.ui.chat.list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signalmatch_frontend.ui.components.TabBar
import com.example.signalmatch_frontend.ui.components.etc.Logo
import com.example.signalmatch_frontend.viewmodel.MypageEntryViewModel
import java.time.LocalDateTime

/*
@Preview
@Composable
fun ChatListScreenPreview () {
    val navController: NavHostController = rememberNavController()

    ChatListScreen(
        navController = navController,
        userId = 40
    )
}*/

@Composable
fun ChatListScreen (
    navController: NavController,
    userId: Int,
    uiState: ChatListUiState,
    mypageViewModel: MypageEntryViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            TabBar(
                navController = navController,
                userId = userId,
                onMypageClick = {
                    mypageViewModel.openMypage(
                        navController = navController,
                        userId = userId,
                        onError = { msg ->
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            )
        },
        containerColor = Color.White
    ) {innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            when (uiState) {
                is ChatListUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is ChatListUiState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = uiState.message,
                            color = Color.Red,
                            fontSize = 16.sp
                        )
                    }
                }

                is ChatListUiState.Success -> {
                    val data = uiState.data

                    Spacer(modifier = Modifier.height(16.dp))
                    Logo(size = 105.dp)
                    ChatListContainer(
                        navController = navController,
                        userId = userId,
                        chatItems = data ?: arrayListOf(
                            ChatListItem(chatId = 1L, name = "Lorem Ipsum", preview="Lorem Ipsum", timestamp = LocalDateTime.now(), isUnread = true),
                            ChatListItem(chatId = 2L, name = "Lorem Ipsum", preview="Lorem Ipsum", timestamp = LocalDateTime.now(), isUnread = false)
                        )
                    )
                }
            }
        }
    }
}