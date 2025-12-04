package com.example.signalmatch_frontend.ui.info

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signalmatch_frontend.ui.components.card.MypageCard3
import com.example.signalmatch_frontend.ui.components.etc.Logo
import com.example.signalmatch_frontend.viewmodel.InfoIRViewModel

@Composable
fun InfoIRScreen(
    documentState: InfoIRViewModel.UiState,
    onGetClick: () -> Unit,
    onDialogDismiss: () -> Unit,
    onDocumentClick: (String) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    LaunchedEffect(documentState) {
        when (documentState) {
            is InfoIRViewModel.UiState.Error -> {
                dialogMessage = documentState.message
                showDialog = true
            }
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(63.dp))
        Logo(105.dp)
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            "IR자료",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.offset(x = (-144).dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        MypageCard3("IR 자료 조회하기", onClick = onGetClick)

        Spacer(modifier = Modifier.height(24.dp))

        when (documentState) {
            InfoIRViewModel.UiState.Idle -> {
            }

            InfoIRViewModel.UiState.Loading -> {
                Text("IR 문서를 불러오는 중입니다...", fontSize = 14.sp)
            }

            is InfoIRViewModel.UiState.Error -> {
                Text(
                    "등록된 IR 자료가 없습니다.",
                    fontSize = 14.sp,
                    color = Color(0xFF848484)
                )
            }

            is InfoIRViewModel.UiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (documentState.documents.isEmpty()) {
                        Text(
                            "등록된 IR 자료가 없습니다.",
                            fontSize = 14.sp,
                            color = Color(0xFF848484)
                        )
                    } else {
                        documentState.documents.forEach { item ->
                            Text(
                                text = item.fileName,
                                fontSize = 15.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onDocumentClick(item.url) }
                                    .padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
