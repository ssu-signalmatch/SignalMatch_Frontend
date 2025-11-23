package com.example.signalmatch_frontend.ui.chat.room

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatRoomContentContainer (
    chatItems: List<ChatItem>
) {
    val scrollState: ScrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp)
            .scrollable(
                state = scrollState,
                orientation = Orientation.Horizontal
            )
    ) {
        ChatRoomContent(
            chatItems = chatItems
        )
    }
}

@Composable
fun ChatRoomContent (
    chatItems: List<ChatItem>
) {
    for (i: Int in 0..<chatItems.size) {
        val item: ChatItem = chatItems[i]
        val isContinuous: Boolean =
            (i > 0 && chatItems[i - 1].getSender() == item.getSender() && chatItems[i - 1].getUserId() == item.getUserId())
            || (chatItems.size > i + 1 && chatItems[i + 1].getSender() == item.getSender() && chatItems[i + 1].getUserId() == item.getUserId())
        val isFirst: Boolean =
            (i == 0)
            || (chatItems[i - 1].getSender() != item.getSender())
            || (chatItems[i - 1].getUserId() != item.getUserId())
        val isLast: Boolean =
            (chatItems.size < i + 2)
            || (chatItems.size > i + 1 && chatItems[i + 1].getSender() != item.getSender())
            || (chatItems.size > i + 1 && chatItems[i + 1].getUserId() != item.getUserId())

        if (isFirst) {
            Spacer(modifier = Modifier.height(30.dp))
        } else {
            Spacer(modifier = Modifier.height(6.dp))
        }

        if (item.getSender() == ChatItemSender.SEND && item.getType() == ChatItemType.TEXT) {
            ChatRoomContentTextSend (
                chatItem = chatItems[i],
                isContinuous = isContinuous,
                isLast = isLast
            )
        } else if (item.getSender() == ChatItemSender.RECEIVE && item.getType() == ChatItemType.TEXT) {
            ChatRoomContentTextReceive (
                chatItem = chatItems[i],
                isContinuous = isContinuous,
                isFirst = isFirst,
                isLast = isLast
            )
        }
    }
}

@Composable
fun ChatRoomContentTextSend (
    chatItem: ChatItem,
    isContinuous: Boolean = false,
    isLast: Boolean = false
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFFAEF1EB),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(
                    top = 8.dp,
                    bottom = 8.dp,
                    start = 12.dp,
                    end = 12.dp
                )
        ) {
            Text(
                text = chatItem.getMessage(),
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
            )
        }

        if (!isContinuous || isLast) { // Non-Continuous or Continuous Last
            Text(
                text = chatItem.getFormattedTimestamp(),
                fontSize = 8.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF848484),
                maxLines = 1
            )
        }
    }
}

@Composable
fun ChatRoomContentTextReceive (
    chatItem: ChatItem,
    isContinuous: Boolean = false,
    isFirst: Boolean = false,
    isLast: Boolean = false
) {
    Row ( // Line
        modifier = Modifier.fillMaxWidth()
    ) {
        if (!isContinuous || isFirst) { // Non-Continuous or Continuous First
            Box ( // Profile Image Box
                modifier = Modifier
                    .size(60.dp, 60.dp)
                    .background(
                        color = Color(0xFFD9D9D9),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                chatItem.getProfileImage()?.let {
                    Image (
                        painter = it,
                        contentDescription = "profile image / ${chatItem.getName()}",
                        contentScale = ContentScale.FillBounds
                    )
                }

            }
            Spacer (modifier = Modifier.width(12.dp))
        } else { // Continuous Non-First
            Spacer (modifier = Modifier.width(72.dp))
        }

        Column ( // Message Box
            modifier = Modifier.fillMaxWidth()
        ) {

            if (!isContinuous || isFirst) { // Non-Continuous or Continuous First
                Text (
                    text = chatItem.getName(),
                    fontSize = 12.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF848484),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer (modifier = Modifier.height(2.dp))
            }

            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFFAFAFA),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        start = 12.dp,
                        end = 12.dp
                    )
            ) {
                Text(
                    text = chatItem.getMessage(),
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
            }

            if (!isContinuous || isLast) { // Non-Continuous or Continuous Last
                Text(
                    text = chatItem.getFormattedTimestamp(),
                    fontSize = 8.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF848484),
                    maxLines = 1
                )
            }
        }
    }
}