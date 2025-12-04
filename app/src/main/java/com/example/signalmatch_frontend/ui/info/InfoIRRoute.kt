package com.example.signalmatch_frontend.ui.info

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signalmatch_frontend.viewmodel.InfoIRViewModel

@Composable
fun InfoIRRoute(
    navController: NavController,
    startupId: Int,
    viewModel: InfoIRViewModel = hiltViewModel()
) {
    val documentState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    InfoIRScreen(
        documentState = documentState,
        onGetClick = {
            viewModel.loadDocuments(startupId)
        },
        onDialogDismiss = {
            viewModel.resetState()
        },
        onDocumentClick = { url ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        }
    )
}
