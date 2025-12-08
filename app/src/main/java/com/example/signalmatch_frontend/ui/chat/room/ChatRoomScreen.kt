package com.example.signalmatch_frontend.ui.chat.room

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.signalmatch_frontend.ui.chat.list.ChatListUiState
import java.time.LocalDateTime


@Composable
fun ChatRoomScreen (
    navController: NavController,
    userId: Long,
    chatroomId: Long,
    chatroomName: String,
    uiState: ChatRoomUiState
) {
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp)
        ) {
            ChatRoomTitle(
                navController = navController,
                title = chatroomName,
                count = 0
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (uiState) {
                    is ChatRoomUiState.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is ChatRoomUiState.Error -> {
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

                    is ChatRoomUiState.Success -> {
                        val data = uiState.data

                        ChatRoomContentContainer (
                            chatItems = data?.map { response ->
                                ChatItem(
                                    userId = userId,
                                    chatroomName = chatroomName,
                                    apiResponse = response
                                )
                            } ?: arrayListOf()
                        )
                    }
                }
                /*ChatRoomContentContainer (
                    chatItems =
                        arrayListOf(
                        ChatItem(
                            chatId = "4",
                            sender = ChatItemSender.RECEIVE,
                            type = ChatItemType.TEXT,
                            userId = "1",
                            name = "코끼리12",
                            message = "끼이끼끼끼끼끼",
                            timestamp = LocalDateTime.of(2025, 11, 21, 9, 43, 30),
                            read = true
                        ),
                        ChatItem(
                            chatId = "3",
                            sender = ChatItemSender.SEND,
                            type = ChatItemType.TEXT,
                            userId = "2",
                            name = "코끼리13",
                            message = "안녕하세요~ 무슨일 때문에 연락하셨을까요?",
                            timestamp = LocalDateTime.of(2025, 11, 21, 9, 43, 30),
                            read = true
                        ),
                        ChatItem(
                            chatId = "4",
                            sender = ChatItemSender.SEND,
                            type = ChatItemType.TEXT,
                            userId = "2",
                            name = "코끼리13",
                            message = "끼끼",
                            timestamp = LocalDateTime.of(2025, 11, 21, 9, 43, 30),
                            read = true
                        ),
                        ChatItem(
                            chatId = "5",
                            sender = ChatItemSender.RECEIVE,
                            type = ChatItemType.TEXT,
                            userId = "1",
                            name = "코끼리12",
                            message = "문의 드리고 싶은 사항이 있어 연락드립니다~",
                            timestamp = LocalDateTime.of(2025, 11, 21, 9, 43, 30),
                            read = true
                        )
                    )
                )*/
            }

            ChatRoomMsgBox(roomId = chatroomId)

        }
    }
}