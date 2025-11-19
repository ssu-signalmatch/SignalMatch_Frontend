package com.example.signalmatch_frontend.ui.chat

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ChatListContainer (
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
                item -> item.get()
            }
        }
    }
}