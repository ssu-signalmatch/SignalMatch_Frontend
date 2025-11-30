package com.example.signalmatch_frontend.ui.startup.profiledetail

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
import com.example.signalmatch_frontend.data.model.response.StartupProfileData

@Composable
fun StartupProfileDetailScreen(
    navController: NavController,
    userId: Int,
    profile: StartupProfileData
) {
    Column( modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(horizontal = 10.dp)
        .verticalScroll(rememberScrollState())
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
                        profile.startupName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            profile.businessAreas.firstOrNull() ?: "",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF848484)
                        )
                        Text(" • ")
                        Text(
                            profile.investorStages,
                            fontSize = 16.sp,
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
                Spacer(modifier = Modifier.width(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_ir),
                        contentDescription = "IR자료",
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "IR 자료 보기",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF848484)
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
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
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "회사 개요",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "• 회사 이름 : " + profile.startupName,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Text(
                "• 기업 설립일 : " + profile.foundingDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Text(
                "• 사업자 번호 : " + profile.businessNumber,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Text(
                "• 대표자명 : " + profile.representativeName,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Text(
                "• 주소 : " + profile.address,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Text(
                "• sns/웹사이트 : " + profile.homepageUrl,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Text(
                "• 운영 여부 : " + profile.status,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Text(
                "• 규모 : " + profile.scale,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Text(
                "• 산업 분야 : " + profile.businessAreas.joinToString(", "),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Text(
                "• 기업 분류 : " + profile.legalType,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "팀 및 구성원 정보",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "• 대표자 약력 : " + profile.history,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Text(
                "• 고용 인원 : " + profile.employeeCount.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "투자 정보",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "• 누적유치금액 : " + "${profile.totalFunding} 원",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Text(
                "• 투자유치건수 : " + "${profile.fundingRounds.toString()} 건",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Text(
                "• 매출액 : " + "${profile.revenue} 원",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Text(
                "• 영업이익 : " + "${profile.profit} 원",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Spacer(modifier = Modifier.height(48.dp))
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(
                onClick = { navController.navigate("startup-edit profile/$userId") },
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
fun StartupProfileDetailPreview(){
    //StartupProfileDetailScreen()
}