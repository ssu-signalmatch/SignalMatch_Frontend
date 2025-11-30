package com.example.signalmatch_frontend.ui.startup.mypage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signalmatch_frontend.viewmodel.StartupMypageViewModel

@Composable
fun StartupMypageRoute(
    navController: NavController,
    userId: Int,
    viewModel: StartupMypageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        viewModel.loadStartupProfile(userId)
    }

    StartupMypageScreen(
        navController = navController,
        userId = userId,
        uiState = uiState
    )
}
