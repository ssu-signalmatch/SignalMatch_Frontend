package com.example.signalmatch_frontend.ui.investor.profiledetail

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.signalmatch_frontend.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.signalmatch_frontend.data.model.response.InvestorProfileData


@Composable
fun InvestorProfileDetailScreen(
    navController: NavController,
    userId: Int,
    profile: InvestorProfileData
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_profile),
                    contentDescription = "프로필 이미지",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Column {
                    Text(
                        profile.investorName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            profile.organizationName,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF848484)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "\"" + " ${profile.intro} " + "\"",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF848484),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_heart),
                        contentDescription = "북마크 수",
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "+346",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF848484)
                    )
                }
                Spacer(modifier = Modifier.width(40.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_update),
                        contentDescription = "업데이트 날짜",
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "2025.05.26",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF848484)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                "투자자 정보",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Column(
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    "• 이름 :" + profile.investorName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF848484)
                )
                Text(
                    "• 연락처 :" + profile.phoneNumber,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF848484)
                )
                Text(
                    "• 연락처 :" + profile.organizationName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF848484)
                )
                Text(
                    "• 이메일 :" + profile.contactEmail,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF848484)
                )
                Text(
                    "• sns/웹사이트 :" + profile.websiteUrl,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF848484)
                )
                Text(
                    "• 투자자 유형 :" + profile.investorType,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF848484)
                )
                Text(
                    "• 투자 희망 규모 :" + profile.preferredInvestmentSize,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF848484)
                )
                Text(
                    "• 선호 산업분야 :" + profile.preferredAreas.joinToString(", "),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF848484)
                )
                Text(
                    "• 선호 투자 단계 :" + profile.preferredStages.joinToString(", "),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF848484)
                )
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(
                onClick = { navController.navigate("investor-edit profile/$userId") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFAEF1EB),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFFAEF1EB).copy(alpha = 0.4f),
                    disabledContentColor = Color.White.copy(alpha = 0.6f)
                ),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = "수정 하기",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview
@Composable
fun InvestorProfileDetailPreview(){
    //InvestorProfileDetailScreen()
}