package com.example.signalmatch_frontend.ui.ir

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import com.example.signalmatch_frontend.ui.components.card.MypageCard3
import com.example.signalmatch_frontend.ui.components.etc.Logo
import com.example.signalmatch_frontend.viewmodel.DocumentUiState
import com.example.signalmatch_frontend.viewmodel.IRUiState


@Composable
fun IRScreen(
    uiState: IRUiState,
    documentState: DocumentUiState,
    onUploadClick: () -> Unit,
    onGetClick: () -> Unit,
    onDialogDismiss: () -> Unit,
    onDocumentClick: (String) -> Unit,
    onDocumentDeleteClick: (Int) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    LaunchedEffect(uiState) {
        when (uiState) {
            is IRUiState.Success -> {
                dialogMessage = "IR 업로드가 완료되었습니다."
                showDialog = true
            }
            is IRUiState.Error -> {
                dialogMessage = uiState.message
                showDialog = true
            }
            else -> showDialog = false
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                onDialogDismiss()
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    onDialogDismiss()
                }) {
                    Text("확인")
                }
            },
            title = { Text("알림") },
            text = { Text(dialogMessage) }
        )
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
            modifier = Modifier.offset((-144).dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        MypageCard3("IR 자료 업로드", onClick = onUploadClick)
        Spacer(modifier = Modifier.height(8.dp))
        MypageCard3("IR 자료 조회하기", onClick = onGetClick)

        Spacer(modifier = Modifier.height(24.dp))

        when (documentState) {
            is DocumentUiState.Loading -> {
                Text("IR 문서를 불러오는 중입니다...", fontSize = 14.sp)
            }
            is DocumentUiState.Error -> {
                Text(
                    text = documentState.message,
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }
            is DocumentUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (documentState.documents.isEmpty()) {
                        Text("등록된 IR 자료가 없습니다.",
                            fontSize = 14.sp,
                            color = Color(0xFF848484)
                        )
                    } else {
                        documentState.documents.forEach { item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = item.fileName,
                                    fontSize = 15.sp,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable { onDocumentClick(item.url) }
                                        .padding(vertical = 8.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "삭제",
                                    color = Color.Red,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .clickable {
                                            onDocumentDeleteClick(item.documentId)
                                        }
                                        .padding(4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
