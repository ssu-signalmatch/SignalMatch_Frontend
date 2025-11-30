package com.example.signalmatch_frontend.ui.components.image

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.signalmatch_frontend.R

@Composable
fun ProfileImage(
    imageUri: Uri?,
    onAddPhotoClick: (() -> Unit)? = null // null일때 클릭 없음
) {
    var boxModifier = Modifier
        .size(100.dp)
        .clip(RoundedCornerShape(20.dp))

    if (onAddPhotoClick != null) {
        boxModifier = boxModifier.clickable { onAddPhotoClick() }
    }

    Box(
        modifier = boxModifier,
        contentAlignment = Alignment.Center
    ) {
        if (imageUri != null) {
            AsyncImage(
                model = imageUri,
                contentDescription = "프로필 이미지",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "사진 추가",
                modifier = Modifier.size(100.dp),
                tint = Color.Unspecified
            )
        }
    }
}
