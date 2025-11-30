package com.example.signalmatch_frontend.ui.mypage.bookmark_list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.signalmatch_frontend.viewmodel.BookmarkViewModel

@Composable
fun BookmarkRoute(
    navController: NavController,
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    BookmarkScreen(
        navController = navController,
        uiState = uiState,
        onRefresh = { viewModel.loadBookmark() },
        onConfirmDelete = { id ->
            viewModel.deleteBookmark(id)
        }
    )
}
