package com.example.signalmatch_frontend.ui.components.etc

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.signalmatch_frontend.R
import com.example.signalmatch_frontend.viewmodel.BookmarkViewModel

@Composable
fun BookmarkIcon(
    targetUserId: Int,
    bookmarkViewModel: BookmarkViewModel,
    onChanged: () -> Unit = {}
) {
    val uiState by bookmarkViewModel.uiState.collectAsState()
    val isBookmarked = uiState.bookmark.any { it.targetUserId == targetUserId }

    Image(
        painter = painterResource(
            id = if (isBookmarked) {
                R.drawable.ic_bookmarkon
            } else {
                R.drawable.ic_bookmarkoff
            }
        ),
        contentDescription = if (isBookmarked) "북마크 해제" else "북마크",
        modifier = Modifier
            .size(30.dp)
            .clickable {
                if (isBookmarked) {
                    bookmarkViewModel.deleteBookmark(targetUserId){
                        onChanged()
                    }
                } else {
                    bookmarkViewModel.addBookmark(targetUserId){
                        onChanged()
                    }
                }
            }
    )
}
