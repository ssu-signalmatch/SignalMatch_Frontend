package com.example.signalmatch_frontend.ui.startup.profilecreate

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signalmatch_frontend.viewmodel.CreateProfileViewModel

@Composable
fun StartupCreateProfileRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CreateProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(uiState) {
        when (uiState) {
            is CreateProfileViewModel.UiState.Success -> {
                navController.navigate("home") {
                    popUpTo("startup_create") { inclusive = true }
                }
            }

            is CreateProfileViewModel.UiState.Error -> {
                Toast.makeText(context, uiState.message, Toast.LENGTH_SHORT).show()
            }

            else -> Unit
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        StartupCreateProfileScreen(
            modifier = Modifier.fillMaxSize(),
            onSubmit = { form ->
                viewModel.submit(form)
            }
        )

        if (uiState is CreateProfileViewModel.UiState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
