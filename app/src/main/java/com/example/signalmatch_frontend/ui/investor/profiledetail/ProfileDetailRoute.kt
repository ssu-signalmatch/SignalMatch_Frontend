package com.example.signalmatch_frontend.ui.investor.profiledetail

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signalmatch_frontend.viewmodel.InvestorProfileDetailViewModel

@Composable
fun InvestorProfileDetailRoute(
    navController: NavController,
    viewModel: InvestorProfileDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is InvestorProfileDetailViewModel.UiState.Loading -> {
            CircularProgressIndicator()
        }

        is InvestorProfileDetailViewModel.UiState.Error -> {
            Text(text = state.message)
        }

        is InvestorProfileDetailViewModel.UiState.Success -> {
            InvestorProfileDetailScreen(
                profile = state.data
            )
        }
    }
}