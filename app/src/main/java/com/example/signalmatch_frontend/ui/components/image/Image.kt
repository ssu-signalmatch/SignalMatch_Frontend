package com.example.signalmatch_frontend.ui.components.image

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.signalmatch_frontend.R
import com.example.signalmatch_frontend.viewmodel.ProfileImageViewModel

@Composable
fun ProfileImageSelector(
    modifier: Modifier = Modifier,
    userId: Int,
    initialImageUrl: String?,
    viewModel: ProfileImageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(initialImageUrl) {
        viewModel.setInitialImageUrl(initialImageUrl)
    }

    var localPreviewUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            localPreviewUri = it
            Log.d("ProfileImageSelector", "uploadProfileImage called, userId=$userId, uri=$it")
            viewModel.uploadProfileImage(userId, it)
        }
    }

    val imageToShow: Any? = when {
        localPreviewUri != null -> localPreviewUri
        uiState.imageUrl != null -> uiState.imageUrl
        else -> null
    }

    Box(
        modifier = modifier
            .size(100.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                galleryLauncher.launch("image/*")
            },
        contentAlignment = Alignment.Center
    ) {
        if (imageToShow != null) {
            AsyncImage(
                model = imageToShow,
                contentDescription = "프로필 이미지",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_profile),
                error = painterResource(R.drawable.ic_profile)
            )
        } else {
            Image(
                painter = painterResource(R.drawable.ic_profile),
                contentDescription = "기본 프로필 이미지",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
