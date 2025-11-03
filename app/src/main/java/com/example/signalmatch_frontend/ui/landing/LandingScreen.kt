package com.example.signalmatch_frontend.ui.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.signalmatch_frontend.ui.components.text.NavigationText
import com.example.signalmatch_frontend.ui.components.etc.Logo

@Composable
fun LandingScreen(navController: NavController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(211.dp))
        Text("스타트업을\n스와이프하세요",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 50.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text("성장을 함께할 파트너를 연결합니다",
            fontSize = 20.sp,
            color = Color(0xFF848484)
        )
        Spacer(modifier = Modifier.height(150.dp))
        Button(
            onClick = {
                navController.navigate("login")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFADF1EB),
                contentColor = Color.White),
            modifier = Modifier
                .width(261.dp)
                .height(62.dp),
            shape = RoundedCornerShape(10.dp)
        ){
            Text("로그인 하러 가기",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        NavigationText(
            question = "아직 계정이 없으신가요?",
            actionText = "회원가입",
            onActionClick = {
                navController.navigate("signup_role")
            }
        )
        Spacer(modifier = Modifier.height(50.dp))
        Logo(149.dp)
    }
}