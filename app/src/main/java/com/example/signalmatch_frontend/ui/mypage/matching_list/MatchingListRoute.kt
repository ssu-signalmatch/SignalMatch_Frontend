package com.example.signalmatch_frontend.ui.mypage.matching_list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.signalmatch_frontend.viewmodel.MatchViewModel

@Composable
fun MatchingListRoute(
    navController: NavController,
    viewModel: MatchViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    MatchingListScreen(
        navController = navController,
        uiState = uiState,
        onRefresh = { viewModel.loadMatches() },
        onCancelMatch = { matchId, reasonCode, reasonText ->
            viewModel.cancelMatch(matchId, reasonCode, reasonText)
        }
    )
}
