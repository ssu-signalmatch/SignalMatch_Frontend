package com.example.signalmatch_frontend.ui.startup.profilecreate

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.signalmatch_frontend.viewmodel.CreateProfileViewModel

@Composable
fun StartupCreateProfileRoute(
    navController: NavController,
    userId: Int,
    viewModel: CreateProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState

    LaunchedEffect(uiState) {
        when (uiState) {
            is CreateProfileViewModel.UiState.Success -> {
                navController.navigate("home/$userId") {
                    popUpTo("post-login") { inclusive = true }
                }
            }
            else -> Unit
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        StartupCreateProfileScreen(
            onSubmit = { form ->
                viewModel.submit(form)
            }
        )

        if (uiState is CreateProfileViewModel.UiState.Loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        if (uiState is CreateProfileViewModel.UiState.Error) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 24.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = uiState.message,
                    color = Color.Red
                )
            }
        }
    }
}
