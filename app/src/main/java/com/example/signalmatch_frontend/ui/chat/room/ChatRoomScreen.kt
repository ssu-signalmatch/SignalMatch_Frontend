package com.example.signalmatch_frontend.ui.chat.room

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime


@Preview
@Composable
fun ChatRoomScreenPreview () {
    ChatRoomScreen()
}

@Composable
fun ChatRoomScreen () {
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
                title = "코끼리 12",
                count = 1000L
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                ChatRoomContentContainer (
                    chatItems = arrayListOf(
                        ChatItem(
                            chatId = "1",
                            sender = ChatItemSender.RECEIVE,
                            type = ChatItemType.TEXT,
                            userId = "1",
                            name = "코끼리12",
                            message = "안녕하세요~ 코끼리 1 입니다.",
                            timestamp = LocalDateTime.of(2025, 11, 21, 9, 43, 30),
                            read = true
                        ),
                        ChatItem(
                            chatId = "2",
                            sender = ChatItemSender.RECEIVE,
                            type = ChatItemType.TEXT,
                            userId = "1",
                            name = "코끼리12",
                            message = "문의 드리고 싶은 사항이 있어 연락드립니다~",
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
                            sender = ChatItemSender.RECEIVE,
                            type = ChatItemType.TEXT,
                            userId = "1",
                            name = "코끼리12",
                            message = "끼이끼끼끼끼끼",
                            timestamp = LocalDateTime.of(2025, 11, 21, 9, 43, 30),
                            read = true
                        ),
                        ChatItem(
                            chatId = "1",
                            sender = ChatItemSender.RECEIVE,
                            type = ChatItemType.TEXT,
                            userId = "1",
                            name = "코끼리12",
                            message = "안녕하세요~ 코끼리 1 입니다.",
                            timestamp = LocalDateTime.of(2025, 11, 21, 9, 43, 30),
                            read = true
                        ),
                        ChatItem(
                            chatId = "2",
                            sender = ChatItemSender.RECEIVE,
                            type = ChatItemType.TEXT,
                            userId = "1",
                            name = "코끼리12",
                            message = "문의 드리고 싶은 사항이 있어 연락드립니다~",
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
                            sender = ChatItemSender.RECEIVE,
                            type = ChatItemType.TEXT,
                            userId = "1",
                            name = "코끼리12",
                            message = "끼이끼끼끼끼끼",
                            timestamp = LocalDateTime.of(2025, 11, 21, 9, 43, 30),
                            read = true
                        ),
                        ChatItem(
                            chatId = "1",
                            sender = ChatItemSender.RECEIVE,
                            type = ChatItemType.TEXT,
                            userId = "1",
                            name = "코끼리12",
                            message = "안녕하세요~ 코끼리 1 입니다.",
                            timestamp = LocalDateTime.of(2025, 11, 21, 9, 43, 30),
                            read = true
                        ),
                        ChatItem(
                            chatId = "2",
                            sender = ChatItemSender.RECEIVE,
                            type = ChatItemType.TEXT,
                            userId = "1",
                            name = "코끼리12",
                            message = "문의 드리고 싶은 사항이 있어 연락드립니다~",
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
                            sender = ChatItemSender.RECEIVE,
                            type = ChatItemType.TEXT,
                            userId = "1",
                            name = "코끼리12",
                            message = "끼이끼끼끼끼끼",
                            timestamp = LocalDateTime.of(2025, 11, 21, 9, 43, 30),
                            read = true
                        )
                    )
                )
            }

            ChatRoomMsgBox()

        }
    }
}