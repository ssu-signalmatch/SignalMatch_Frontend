package com.example.signalmatch_frontend.ui.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun FAQScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(63.dp))
        Logo(105.dp)
        Spacer(modifier = Modifier.height(5.dp))
        Text("FAQ (자주 묻는 질문)",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.offset((-104).dp))
        Spacer(modifier = Modifier.height(10.dp))
        MypageCard3("회원 탈퇴는 어떻게 하나요?", onClick = { })
        Spacer(modifier = Modifier.height(5.dp))
        MypageCard3("매칭 서비스의 기준은 무엇인가요?", onClick = { })

    }
}

@Composable
@Preview
fun FAQPreview(){
    val navController = rememberNavController()
    FAQScreen(navController)
}