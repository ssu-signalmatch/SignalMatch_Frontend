package com.example.signalmatch_frontend.ui.info

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signalmatch_frontend.viewmodel.BookmarkViewModel
import com.example.signalmatch_frontend.viewmodel.InvestorInfoViewModel
import com.example.signalmatch_frontend.viewmodel.SearchViewModel

@Composable
fun InvestorInfoRoute(
    navController: NavController,
    userId: Int,
    infoViewModel: InvestorInfoViewModel = hiltViewModel(),
    bookmarkViewModel: BookmarkViewModel = hiltViewModel()
) {
    val uiState by infoViewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        infoViewModel.loadInfo(userId)
        bookmarkViewModel.loadBookmarks()
    }

    when (val state = uiState) {
        is InvestorInfoViewModel.UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is InvestorInfoViewModel.UiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.message)
            }
        }

        is InvestorInfoViewModel.UiState.Success -> {
            val investor = state.investor
            val profileImageUrl = state.profileImageUrl
            val lastUpdatedDate = state.updatedAt

            InvestorInfoScreen(
                navController = navController,
                userId = userId,
                investorInfo = investor,
                profileImageUrl = profileImageUrl,
                lastUpdatedDate = lastUpdatedDate,
                bookmarkViewModel = bookmarkViewModel,
                bookmarkCount = state.bookmarkCount,
                onRefreshInfo = {
                    infoViewModel.loadInfo(userId)
                }
            )
        }
    }
}
