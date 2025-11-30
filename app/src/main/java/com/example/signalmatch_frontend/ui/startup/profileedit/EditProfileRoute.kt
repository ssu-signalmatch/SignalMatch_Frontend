package com.example.signalmatch_frontend.ui.startup.profileedit

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
import com.example.signalmatch_frontend.data.model.request.StartupEditProfileRequest
import com.example.signalmatch_frontend.ui.startup.profilecreate.StartupProfileForm
import com.example.signalmatch_frontend.viewmodel.StartupEditProfileViewModel

@Composable
fun StartupEditProfileRoute(
    navController: NavController,
    userId: Int,
    viewModel: StartupEditProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    // 화면 들어올 때 프로필 조회
    LaunchedEffect(userId) {
        viewModel.loadProfile(userId)
    }

    when (val state = uiState) {
        StartupEditProfileUiState.Idle,
        StartupEditProfileUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is StartupEditProfileUiState.Loaded -> {
            StartupEditProfileScreen(
                initialForm = state.form,
                onSubmit = { form: StartupProfileForm ->
                    val req = StartupEditProfileRequest(
                        startupName = form.startupName,
                        status = form.status,
                        foundingDate = form.foundingDate,
                        address = form.address,
                        homepageUrl = form.homepageUrl,
                        contactEmail = form.contactEmail,
                        intro = form.intro,
                        representativeName = form.representativeName,
                        businessNumber = form.businessNumber,
                        employeeCount = form.employeeCount,
                        legalType = form.legalType,
                        scale = form.scale,
                        revenue = form.revenue.toInt(),
                        profit = form.profit.toInt(),
                        fundingRounds = form.fundingRounds,
                        totalFunding = form.totalFunding.toInt(),
                        investorStages = form.investorStage,
                        businessAreas = form.businessAreas.toList(),
                        history = form.history
                    )
                    viewModel.editProfile(req)
                }
            )
        }

        is StartupEditProfileUiState.Success -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()

            navController.previousBackStackEntry
                ?.savedStateHandle
                ?.set("refresh_startup_profile", true)

            viewModel.resetState()
            navController.popBackStack()
        }

        is StartupEditProfileUiState.Error -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            viewModel.resetState()
        }
    }
}
