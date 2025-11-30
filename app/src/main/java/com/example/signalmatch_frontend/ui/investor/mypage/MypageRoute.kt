package com.example.signalmatch_frontend.ui.investor.mypage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signalmatch_frontend.viewmodel.InvestorMypageViewModel

@Composable
fun InvestorMypageRoute(
    navController: NavController,
    userId: Int,
    viewModel: InvestorMypageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        viewModel.loadInvestorProfile(userId)
    }

    InvestorMypageScreen(
        navController = navController,
        userId = userId,
        uiState = uiState
    )
}
