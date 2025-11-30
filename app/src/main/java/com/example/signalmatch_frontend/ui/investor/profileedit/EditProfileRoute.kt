package com.example.signalmatch_frontend.ui.investor.profileedit

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signalmatch_frontend.data.model.request.InvestorEditProfileRequest
import com.example.signalmatch_frontend.viewmodel.InvestorEditProfileViewModel

@Composable
fun InvestorEditProfileRoute(
    navController: NavController,
    userId: Int,
    viewModel: InvestorEditProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(userId) {
        viewModel.loadProfile(userId)
    }

    when (val state = uiState) {
        InvestorEditProfileUiState.Idle,
        InvestorEditProfileUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is InvestorEditProfileUiState.Loaded -> {
            InvestorProfileScreen(
                initialForm = state.form,
                onSubmit = { form ->
                    val req = InvestorEditProfileRequest(
                        investorName = form.investorName,
                        contactEmail = form.contactEmail,
                        phoneNumber = form.phoneNumber,
                        organizationName = form.organizationName,
                        websiteUrl = form.websiteUrl,
                        intro = form.intro,
                        investorType = form.investorType,
                        preferredInvestmentSize = form.preferredInvestmentSize,
                        preferredStages = form.preferredStages.toList(),
                        preferredAreas = form.preferredAreas.toList()
                    )
                    viewModel.editProfile(req)
                }
            )
        }

        is InvestorEditProfileUiState.Success -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()

            navController.previousBackStackEntry
                ?.savedStateHandle
                ?.set("refresh_investor_profile", true)

            viewModel.resetState()
            navController.popBackStack()
        }

        is InvestorEditProfileUiState.Error -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            viewModel.resetState()
        }
    }
}
