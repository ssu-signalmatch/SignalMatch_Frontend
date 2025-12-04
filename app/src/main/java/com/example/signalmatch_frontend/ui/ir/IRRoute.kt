package com.example.signalmatch_frontend.ui.ir

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signalmatch_frontend.viewmodel.IRViewModel

@Composable
fun IRRoute(
    navController: NavController,
    startupId: Int,
    viewModel: IRViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val documentState by viewModel.documentUiState.collectAsState()
    val context = LocalContext.current
    val contentResolver = context.contentResolver

    var selectedUri: Uri? = null

    val pdfPickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->
            if (uri != null) {
                selectedUri = uri
                viewModel.uploadIr(
                    contentResolver = contentResolver,
                    uri = uri,
                    startupId = startupId
                )
            }
        }

    IRScreen(
        uiState = uiState,
        documentState = documentState,
        onUploadClick = {
            pdfPickerLauncher.launch("application/pdf")
        },
        onGetClick = {
            viewModel.loadDocuments()
        },
        onDialogDismiss = { viewModel.resetState() },
        onDocumentClick = { url ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        },
        onDocumentDeleteClick = { documentId ->
            viewModel.deleteDocument(documentId)
        }
    )

}
