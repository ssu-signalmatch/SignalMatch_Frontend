package com.example.signalmatch_frontend.ui.signup

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
import com.example.signalmatch.viewmodel.SignupViewModel
import com.example.signalmatch_frontend.ui.components.etc.Logo
import com.example.signalmatch_frontend.ui.components.text.NavigationText
import com.example.signalmatch_frontend.ui.components.text.OutlinedTextField

@Composable
fun SignupScreen(
    navController: NavController,
    userRole: String,
    viewModel: SignupViewModel = hiltViewModel(),
){
    var loginId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var signupConfirmPassword by remember { mutableStateOf("") }

    val signupMessage = viewModel.signupMessage
    val signupSuccess = viewModel.signupSuccess

    LaunchedEffect(signupSuccess) {
        if (signupSuccess == true) {
            if (userRole == "STARTUP") {
                navController.navigate("startup_create_profile")
            } else if (userRole == "INVESTOR") {
                navController.navigate("investor_create_profile")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(108.dp))
        Logo(149.dp)
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = "이름"
        )
        OutlinedTextField(
            value = loginId,
            onValueChange = { loginId = it },
            label = "아이디"
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = "비밀번호"
        )
        OutlinedTextField(
            value = signupConfirmPassword,
            onValueChange = { signupConfirmPassword = it },
            label = "비밀번호 확인"
        )

        if (signupMessage == "이미 존재하는 아이디입니다. 다시 입력해주세요.") {
            Text(
                text = signupMessage ?: "",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = {
                if (password == signupConfirmPassword) {
                    viewModel.signup(
                        loginId = loginId,
                        password = password,
                        name = name,
                        userRole = userRole
                    )
                }
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFADF1EB),
                contentColor = Color.White
            ),
            modifier = Modifier
                .width(357.dp)
                .height(62.dp)
        ) {
            Text(
                "회원가입",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        NavigationText(
            question = "이미 계정이 있으신가요?",
            actionText = "로그인",
            onActionClick = {
                navController.navigate("login")
            }
        )
        if (signupMessage == "회원가입이 완료되었습니다.") {
            LaunchedEffect(Unit) {
                navController.navigate("login") {
                    popUpTo("signup-role") { inclusive = true }
                }
            }
        }
    }
}

/*@Preview
@Composable
fun SignupPreview(){
    SignalMatchTheme{
    val navController = rememberNavController()
        SignupScreen(navController = navController)
    }
}*/