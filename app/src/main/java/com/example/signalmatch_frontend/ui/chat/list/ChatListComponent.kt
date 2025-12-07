
package com.example.signalmatch_frontend.ui.chat.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter


@Composable
fun ChatListContainer (
    navController: NavController,
    userId: Int,
    chatItems: List<ChatListItem>
) {
    val scrollState: ScrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .scrollable(
                state = scrollState,
                orientation = Orientation.Horizontal
            )
    ) {
        LazyColumn {
            items(chatItems) {
                item -> ChatListItemUnit(
                    navController = navController,
                    userId = userId,
                    chatItem = item
                )
            }
        }
    }
}

@Composable
fun ChatListItemUnit (
    navController: NavController,
    userId: Int,
    chatItem: ChatListItem
): Unit {
    Button (
        onClick = {
            navController.navigate("chat-room/${userId}/${chatItem.getChatId()}/${chatItem.getName()}")
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 10.dp,
            bottom = 10.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            disabledContainerColor = Color.LightGray
        )
    ) {
        Row (
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box (
                modifier = Modifier
                    .size(60.dp, 60.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) { // Profile Image Box
                chatItem.getProfileImage()?.let {
                    Image (
                        painter = rememberAsyncImagePainter(
                            model = chatItem.getProfileImage()
                        ),
                        contentDescription = "profile image / ${chatItem.getName()}",
                        contentScale = ContentScale.FillBounds
                    )
                }
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 6.dp,
                        bottom = 6.dp,
                        start = 0.dp,
                        end = 0.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) { // Content Row Layout

                Column (
                    modifier = Modifier
                        .widthIn(max = 232.dp)
                        .fillMaxHeight()
                    ,
                    verticalArrangement = Arrangement.SpaceBetween
                ) { // Title, Preview Column Layout
                    Text (
                        text = chatItem.getName() ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text (
                        text = chatItem.getPreview() ?: "",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF848484),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column ( // Date, Badge Column Layout
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(
                        space = 8.dp,
                        alignment = Alignment.Bottom
                    )
                ) {
                    if (chatItem.getIsUnread()) {
                        Text(
                            modifier = Modifier
                                .height(16.dp)
                                .width(16.dp)
                                .background(
                                    color = Color(0xFFAEF1EB),
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            text = "N",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            maxLines = 1,
                            lineHeight = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    Text (
                        text = chatItem.getFormattedTimestamp() ?: "",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF848484),
                        maxLines = 1,
                        overflow = TextOverflow.Visible
                    )
                }
            }
        }
    }
}
