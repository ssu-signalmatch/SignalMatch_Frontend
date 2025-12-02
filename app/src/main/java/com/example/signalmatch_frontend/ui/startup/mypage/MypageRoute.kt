package com.example.signalmatch_frontend.ui.startup.mypage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.DisposableEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.signalmatch_frontend.viewmodel.StartupMypageViewModel

@Composable
fun StartupMypageRoute(
    navController: NavController,
    userId: Int,
    viewModel: StartupMypageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val lifecycle = navBackStackEntry?.lifecycle

    // 🔥 마이페이지가 다시 보여질 때마다 최신 프로필 자동 로딩
    DisposableEffect(lifecycle, userId) {
        if (lifecycle == null) return@DisposableEffect onDispose {}
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.loadStartupProfile(userId)
            }
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }

    StartupMypageScreen(
        navController = navController,
        userId = userId,
        uiState = uiState
    )
}
