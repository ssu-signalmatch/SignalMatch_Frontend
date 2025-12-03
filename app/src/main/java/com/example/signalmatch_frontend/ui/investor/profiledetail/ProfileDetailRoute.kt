package com.example.signalmatch_frontend.ui.investor.profiledetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signalmatch_frontend.viewmodel.InvestorProfileDetailViewModel

@Composable
fun InvestorProfileDetailRoute(
    navController: NavController,
    userId: Int,
    viewModel: InvestorProfileDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val lastUpdatedFromVm by viewModel.lastUpdatedDate.collectAsState(initial = null)

    val currentBackStackEntry = navController.currentBackStackEntry
    val updatedAtState = currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow("investor_profile_updated_at", null)

    val updatedAt by updatedAtState?.collectAsState()
        ?: remember { mutableStateOf<String?>(null) }

    val lastUpdatedToShow = updatedAt ?: lastUpdatedFromVm

    LaunchedEffect(userId) {
        viewModel.refresh()
    }

    when (val state = uiState) {
        is InvestorProfileDetailViewModel.UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is InvestorProfileDetailViewModel.UiState.Error -> {
            Text(text = state.message)
        }

        is InvestorProfileDetailViewModel.UiState.Success -> {
            InvestorProfileDetailScreen(
                navController = navController,
                userId = userId,
                profile = state.profile,
                profileImageUrl = state.profileImageUrl,
                bookmarkCount = state.bookmarkCount,
                lastUpdatedDate = lastUpdatedToShow,
            )
        }
    }
}