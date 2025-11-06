package com.example.signalmatch_frontend.ui.login

import com.example.signalmatch_frontend.viewmodel.LoginViewModel
import android.widget.Toast
import com.example.signalmatch_frontend.ui.components.text.OutlinedTextField
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.signalmatch_frontend.ui.components.text.NavigationText
import com.example.signalmatch_frontend.ui.components.etc.Logo
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun LoginScreen(navController: NavController,
                viewModel: LoginViewModel = hiltViewModel()
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){

        val context = LocalContext.current
        val loginSuccess = viewModel.loginSuccess

        var loginId by remember { mutableStateOf("") }
        var loginPassword by remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(148.dp))
        Logo(149.dp)
        Spacer(modifier = Modifier.height(36.dp))
        OutlinedTextField(
            value = loginId,
            onValueChange = { loginId = it },
            label = "아이디"
        )
        OutlinedTextField(
            value = loginPassword,
            onValueChange = { loginPassword = it },
            label = "비밀번호"
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                viewModel.login(loginId, loginPassword)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFADF1EB),
                contentColor = Color.White),
            modifier = Modifier
                .width(357.dp)
                .height(62.dp),
            shape = RoundedCornerShape(10.dp)
        ){
            Text("로그인",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold)
        }
        NavigationText(
            question = "아직 계정이 없으신가요?",
            actionText = "회원가입",
            onActionClick = {
                navController.navigate("signup_role")
            }
        )
        LaunchedEffect(loginSuccess) {
            when (loginSuccess) {
                true -> {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
                false -> {
                    Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
                null -> { /*공백*/}
            }
        }
    }
}

