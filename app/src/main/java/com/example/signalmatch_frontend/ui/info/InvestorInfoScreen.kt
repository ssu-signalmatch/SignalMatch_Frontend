package com.example.signalmatch_frontend.ui.info


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.signalmatch_frontend.R
import com.example.signalmatch_frontend.data.model.response.InvestorInfo
import com.example.signalmatch_frontend.ui.components.image.ProfileImageSelector

@Composable
fun InvestorInfoScreen(
    navController: NavController,
    userId: Int,
    investorInfo: InvestorInfo,
    profileImageUrl: String?,
    lastUpdatedDate: String?,
    bookmarkCount: Int
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

            ProfileImageSelector(
                userId = userId,
                initialImageUrl = profileImageUrl,
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "\" ${investorInfo.intro} \"",
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
                        text = "+$bookmarkCount",
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
                        lastUpdatedDate?.replace("-", ".")
                            ?: "수정 기록 없음",
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
                Text("• 이름 : ${investorInfo.investorName}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
                Text("• 연락처 : ${investorInfo.phoneNumber}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
                Text("• 소속 : ${investorInfo.organizationName}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
                Text("• 이메일 : ${investorInfo.contactEmail}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
                Text("• SNS/웹사이트 : ${investorInfo.websiteUrl}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
                Text("• 투자자 유형 : ${investorInfo.investorType}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
                Text("• 투자 희망 규모 : ${investorInfo.preferredInvestmentSize}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
                Text(
                    "• 선호 산업분야 : ${
                        investorInfo.preferredAreas?.joinToString(", ") ?: "-"
                    }",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF848484)
                )

                Text(
                    "• 선호 투자 단계 : ${
                        investorInfo.preferredStages?.joinToString(", ") ?: "-"
                    }",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF848484)
                )

                Spacer(modifier = Modifier.height(48.dp))

            }

        }
    }
}



