package com.example.signalmatch_frontend.ui.startup.profiledetail

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signalmatch_frontend.ui.investor.profiledetail.InvestorProfileDetailScreen
import com.example.signalmatch_frontend.viewmodel.InvestorProfileDetailViewModel
import com.example.signalmatch_frontend.viewmodel.StartupProfileDetailViewModel

@Composable
fun StartupProfileDetailRoute(
    navController: NavController,
    viewModel: StartupProfileDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is StartupProfileDetailViewModel.UiState.Loading -> {
            CircularProgressIndicator()
        }

        is StartupProfileDetailViewModel.UiState.Error -> {
            Text(text = state.message)
        }

        is StartupProfileDetailViewModel.UiState.Success -> {
            StartupProfileDetailScreen(
                profile = state.data
            )
        }
    }
}