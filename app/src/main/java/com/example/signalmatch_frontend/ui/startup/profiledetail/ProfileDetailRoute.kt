package com.example.signalmatch_frontend.ui.startup.profiledetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signalmatch_frontend.viewmodel.StartupProfileDetailViewModel

@Composable
fun StartupProfileDetailRoute(
    navController: NavController,
    userId: Int,
    viewModel: StartupProfileDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        viewModel.refresh()
    }

    val currentBackStackEntry = navController.currentBackStackEntry
    val refreshFlow = currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow("refresh_startup_profile", false)

    val shouldRefresh by refreshFlow?.collectAsState()
        ?: remember { mutableStateOf(false) }

    LaunchedEffect(shouldRefresh) {
        if (shouldRefresh) {
            viewModel.refresh()
            currentBackStackEntry
                ?.savedStateHandle
                ?.set("refresh_startup_profile", false)
        }
    }

    when (val state = uiState) {
        is StartupProfileDetailViewModel.UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is StartupProfileDetailViewModel.UiState.Error -> {
            Text(text = state.message)
        }

        is StartupProfileDetailViewModel.UiState.Success -> {
            StartupProfileDetailScreen(
                navController = navController,
                userId = userId,
                profile = state.data
            )
        }
    }
}
