package com.example.signalmatch_frontend.ui.chat.room

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.signalmatch_frontend.viewmodel.ChatRoomViewModel

@Composable
fun ChatRoomRoute (
    navController: NavController,
    userId: Long,
    roomId: Long,
    roomName: String,
    viewModel: ChatRoomViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val lifecycle = navBackStackEntry?.lifecycle

    DisposableEffect(lifecycle, userId) {
        if (lifecycle == null) return@DisposableEffect onDispose {}
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.initChatMessages(
                    roomId = roomId
                )
            }
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }

    ChatRoomScreen(
        navController = navController,
        userId = userId,
        chatroomId = roomId,
        chatroomName = roomName,
        uiState = uiState
    )

}