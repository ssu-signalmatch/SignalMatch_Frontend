package com.example.signalmatch_frontend.ui.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.signalmatch.viewmodel.SignoutViewModel
import com.example.signalmatch_frontend.ui.components.card.MypageCard3
import com.example.signalmatch_frontend.ui.components.etc.Logo

@Composable
fun ManageAccountScreen(
    navController: NavController,
    viewModel: SignoutViewModel = hiltViewModel()
){

    var showWithdrawDialog by remember { mutableStateOf(false) }

    val signoutMessage = viewModel.signoutMessage
    val signoutSuccess = viewModel.signoutSuccess

    LaunchedEffect(signoutSuccess) {
        if (signoutSuccess == true) {
            navController.navigate("landing") {
                popUpTo(0) { inclusive = true }
            }
            viewModel.resetSignoutState()
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
            "계정관리",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.offset((-144).dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        MypageCard3("회원탈퇴 하기", onClick = { showWithdrawDialog = true })
    }

        if (!signoutMessage.isNullOrBlank()) {
            Text(
                text = signoutMessage,
                fontSize = 12.sp,
                color = if (signoutSuccess == true) Color(0xFF4CAF50) else Color.Red
            )
        }

        if (showWithdrawDialog) {
            AlertDialog(
                onDismissRequest = { showWithdrawDialog = false },
                title = {
                    Text(text = "회원 탈퇴")
                },
                text = {
                    Text(
                        text = "정말로 삭제 하시겠습니까?\n" +
                                "탈퇴 시 계정 정보가 모두 삭제되며 복구가 불가능합니다."
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showWithdrawDialog = false
                            viewModel.signout()
                        }
                    ) {
                        Text(
                            text = "삭제하기",
                            color = Color.Red
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showWithdrawDialog = false }
                    ) {
                        Text(text = "취소")
                    }
                }
            )
        }
    }

@Composable
@Preview
fun ManageAccountPreview(){
    val navController = rememberNavController()
    ManageAccountScreen(navController)
}