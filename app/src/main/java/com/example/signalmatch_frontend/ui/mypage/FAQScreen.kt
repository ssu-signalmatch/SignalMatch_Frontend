package com.example.signalmatch_frontend.ui.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signalmatch_frontend.ui.components.card.MypageCard3
import com.example.signalmatch_frontend.ui.components.etc.Logo
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun FAQScreen(navController: NavController) {

    var showDialog1 by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }

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
            "FAQ (자주 묻는 질문)",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.offset((-104).dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        MypageCard3("회원 탈퇴는 어떻게 하나요?", onClick = {
            showDialog1 = true
        })
        Spacer(modifier = Modifier.height(5.dp))

        MypageCard3("매칭 서비스의 기준은 무엇인가요?", onClick = {
            showDialog2 = true
        })
    }

    if (showDialog1) {
        AlertDialog(
            onDismissRequest = { showDialog1 = false },
            title = { Text("회원탈퇴") },
            text = { Text("회원 탈퇴는 앱 우측 하단의 '마이페이지 → 계정관리 → 회원탈퇴하기' 순서로 진행하실 수 있습니다.\n" +"이용해주셔서 감사합니다.")},
            confirmButton = {
                TextButton(onClick = { showDialog1 = false }) {
                    Text("확인")
                }
            }
        )
    }

    if (showDialog2) {
        AlertDialog(
            onDismissRequest = { showDialog2 = false },
            title = { Text("매칭 서비스 기준") },
            text = { Text("SignalMatch의 매칭 기준은 AI 기반 알고리즘입니다.\n" +
                    "사용자의 투자 성향, 업종 선호, 성장 단계 및 스타트업 특성을 종합적으로 분석하여 가장 적합한 매칭을 제공합니다.") },
            confirmButton = {
                TextButton(onClick = { showDialog2 = false }) {
                    Text("확인")
                }
            }
        )
    }
}

@Preview
@Composable
fun FAQPreview() {
    val navController = rememberNavController()
    FAQScreen(navController)
}
