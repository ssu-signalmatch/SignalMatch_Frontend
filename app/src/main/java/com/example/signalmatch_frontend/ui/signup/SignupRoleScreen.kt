package com.example.signalmatch_frontend.ui.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.signalmatch_frontend.R
import com.example.signalmatch_frontend.ui.components.etc.Logo

@Composable
fun SignupRoleScreen(navController: NavController){

    var selectedRole by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(148.dp))
        Logo(149.dp)
        Spacer(modifier = Modifier.height(37.dp))
        Row() {
            OutlinedButton(
                onClick = {selectedRole = "INVESTOR"},
                modifier = Modifier
                    .height(205.dp)
                    .width(170.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (selectedRole == "INVESTOR") Color(0xFFEAEAEA) else Color.White
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(
                        painter = painterResource(R.drawable.ic_investor),
                        contentDescription = "투자사/개인 아이콘",
                        modifier = Modifier.size(100.dp)
                    )
                    Text(
                        "Investor /\nIndividual",
                        fontSize = 20.sp,
                        color = Color(0xFF747474),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.width(13.dp))
            OutlinedButton(
                onClick = {selectedRole = "STARTUP"},
                modifier = Modifier
                    .height(205.dp)
                    .width(170.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (selectedRole == "STARTUP") Color(0xFFEAEAEA) else Color.White
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(
                        painter = painterResource(R.drawable.ic_startup),
                        contentDescription = "스타트업 아이콘",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Start-up",
                        fontSize = 20.sp,
                        color = Color(0xFF747474)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(43.dp))
        Button(
            onClick = {
                selectedRole?.let { userRole ->
                    navController.navigate("signup/$userRole")
                }
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .height(62.dp)
                .width(225.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFADF1EB),
                contentColor = Color.White),
        ) {
            Text("Next",
                fontSize = 20.sp)
        }

    }
}

@Preview
@Composable
fun SignupRolePreview(){
        val navController = rememberNavController()
        SignupRoleScreen(navController = navController)
}