package com.example.signalmatch_frontend.ui.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
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
import com.example.signalmatch_frontend.data.model.response.StartupInfo
import com.example.signalmatch_frontend.ui.components.etc.BookmarkIcon
import com.example.signalmatch_frontend.ui.components.image.ProfileImageSelector
import com.example.signalmatch_frontend.viewmodel.BookmarkViewModel

@Composable
fun StartupInfoScreen(
    navController: NavController,
    userId: Int,
    startupInfo: StartupInfo,
    profileImageUrl: String?,
    lastUpdatedDate: String?,
    bookmarkViewModel: BookmarkViewModel,
    bookmarkCount: Int,
    onRefreshInfo: () -> Unit
) {
    val introText = startupInfo.intro ?: ""

    Column(
        modifier = Modifier
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
            Spacer(modifier = Modifier.height(60.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 50.dp),
                horizontalAlignment = Alignment.End,
            ) {
                BookmarkIcon(
                    targetUserId = userId,
                    bookmarkViewModel = bookmarkViewModel,
                    onChanged = onRefreshInfo
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileImageSelector(
                userId = userId,
                initialImageUrl = profileImageUrl,
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "\" $introText \"",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF848484),
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
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

                Spacer(modifier = Modifier.width(20.dp))

                Row(
                    modifier = Modifier.clickable {
                        navController.navigate("info-ir/$userId")
                    },
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

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.ic_update),
                        contentDescription = "업데이트 날짜",
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = lastUpdatedDate
                            ?.replace("-", ".")
                            ?: "수정 기록 없음",
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
            Text("• 회사 이름 : ${startupInfo.startupName}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
            Text("• 기업 설립일 : ${startupInfo.foundingDate}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
            Text("• 사업자 번호 : ${startupInfo.businessNumber ?: "-"}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
            Text("• 대표자명 : ${startupInfo.representativeName ?: "-"}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
            Text("• 주소 : ${startupInfo.address}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
            Text("• sns/웹사이트 : ${startupInfo.homepageUrl ?: "-"}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
            Text("• 운영 여부 : ${startupInfo.status}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
            Text("• 규모 : ${startupInfo.scale ?: "-"}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
            Text(
                "• 산업 분야 : ${
                    startupInfo.businessAreas?.joinToString(", ") ?: "-"
                }",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF848484)
            )
            Text("• 기업 분류 : ${startupInfo.legalType ?: "-"}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))

            Spacer(modifier = Modifier.height(24.dp))
            Text("팀 및 구성원 정보", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            Text("• 대표자 약력 : ${startupInfo.history ?: "-"}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
            Text("• 고용 인원 : ${startupInfo.employeeCount?.toString() ?: "-"}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))

            Spacer(modifier = Modifier.height(24.dp))
            Text("투자 정보", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            Text("• 누적유치금액 : ${startupInfo.totalFunding?.let { "${it} 원" } ?: "-"}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
            Text("• 투자유치건수 : ${startupInfo.fundingRounds?.let { "${it} 건" } ?: "-"}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
            Text("• 매출액 : ${startupInfo.revenue?.let { "${it} 원" } ?: "-"}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))
            Text("• 영업이익 : ${startupInfo.profit?.let { "${it} 원" } ?: "-"}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF848484))

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}
