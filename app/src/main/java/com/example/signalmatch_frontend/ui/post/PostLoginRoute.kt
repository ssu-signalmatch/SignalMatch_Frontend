package com.example.signalmatch_frontend.ui.post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signalmatch_frontend.viewmodel.PostLoginViewModel

@Composable
fun PostLoginRoute(
    navController: NavController,
    userId: Int,
    userRole: String,
    viewModel: PostLoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(userId, userRole) {
        viewModel.decideNext(userId, userRole)
    }

    LaunchedEffect(uiState) {
        when (uiState) {
            PostLoginViewModel.UiState.GoHome -> {
                navController.navigate("home") {
                    popUpTo("post-login/{userId}/{userRole}") { inclusive = true }
                }
            }

            PostLoginViewModel.UiState.GoInvestorCreateProfile -> {
                navController.navigate("investor-create profile") {
                    popUpTo("post-login/{userId}/{userRole}") { inclusive = true }
                }
            }

            PostLoginViewModel.UiState.GoStartupCreateProfile -> {
                navController.navigate("startup-create profile") {
                    popUpTo("post-login/{userId}/{userRole}") { inclusive = true }
                }
            }

            is PostLoginViewModel.UiState.Error -> {
                navController.navigate("login") {
                    popUpTo("post-login/{userId}/{userRole}") { inclusive = true }
                }
            }

            PostLoginViewModel.UiState.Loading -> Unit
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
