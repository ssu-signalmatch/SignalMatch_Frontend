package com.example.signalmatch_frontend.ui.startup.profileedit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signalmatch_frontend.ui.components.etc.Logo
import com.example.signalmatch_frontend.ui.components.text.ProfileOutlinedTextField
import com.example.signalmatch_frontend.ui.components.dropdown.ProfileExposedDropdown
import com.example.signalmatch_frontend.ui.components.dropdown.ProfileExposedDropdownMulti
import com.example.signalmatch_frontend.ui.startup.profilecreate.StartupProfileForm

private val SCALE = listOf(
    "LARGE","MID","SMALL"
)

private val STATUS_TYPE = listOf(
    "CLOSED","MERGED","OPERATING","PENDING"
)

private val LEGAL_TYPE = listOf(
    "CORP","GP","INC","LLC","LLP","LP","LTD"
)

private val INDUSTRY_AREAS = listOf(
    "AGRICULTURE_FORESTRY_FISHING", "MINING", "MANUFACTURING",
    "ELECTRICITY_GAS_STEAM_AC", "WATER_SEWAGE_WASTE", "CONSTRUCTION",
    "WHOLESALE_RETAIL", "TRANSPORTATION_WAREHOUSING", "ACCOMMODATION_FOOD",
    "INFORMATION_COMMUNICATION", "FINANCE_INSURANCE", "REAL_ESTATE",
    "PROFESSIONAL_SCIENTIFIC_TECH", "BUSINESS_SUPPORT_RENTAL",
    "PUBLIC_ADMIN_DEFENSE", "EDUCATION", "HEALTH_SOCIAL_WORK",
    "ARTS_SPORTS_RECREATION", "ASSOCIATIONS_REPAIR_PERSONAL",
    "HOUSEHOLD_EMPLOYMENT", "INTERNATIONAL_FOREIGN_ORG"
)

private val FUNDING_STAGE = listOf(
    "LATE","PRE_A","SEED","SERIES_A","SERIES_B","SERIES_C"
)

@Composable
fun StartupEditProfileScreen(
    modifier: Modifier = Modifier,
    initialForm: StartupProfileForm,
    onSubmit: (StartupProfileForm) -> Unit = {}
) {
    var history by remember { mutableStateOf(initialForm.history) } // 대표자 약력
    var startupName by remember { mutableStateOf(initialForm.startupName) }
    var foundingDate by remember { mutableStateOf(initialForm.foundingDate) }
    var scale by remember { mutableStateOf(initialForm.scale) }
    var businessNumber by remember { mutableStateOf(initialForm.businessNumber) }
    var representativeName by remember { mutableStateOf(initialForm.representativeName) }
    var address by remember { mutableStateOf(initialForm.address) }
    var homepageUrl by remember { mutableStateOf(initialForm.homepageUrl) }
    var contactEmail by remember { mutableStateOf(initialForm.contactEmail) }
    var intro by remember { mutableStateOf(initialForm.intro) }
    var status by remember { mutableStateOf(initialForm.status) }
    var businessAreas by remember { mutableStateOf(initialForm.businessAreas) } // 복수 선택
    var legalType by remember { mutableStateOf(initialForm.legalType) }
    var investorStage by remember { mutableStateOf(initialForm.investorStage) }
    var employeeCount by remember { mutableStateOf(initialForm.employeeCount.toString()) }
    var totalFunding by remember { mutableStateOf(initialForm.totalFunding.toString()) }
    var fundingRounds by remember { mutableStateOf(initialForm.fundingRounds.toString()) }
    var revenue by remember { mutableStateOf(initialForm.revenue.toString()) }
    var profit by remember { mutableStateOf(initialForm.profit.toString()) }

    val scroll = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .background(Color.White)
            .padding(start = 15.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(77.dp))
        Logo(98.dp)
        Spacer(modifier = Modifier.height(7.dp))

        Text(
            "회사 개요",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = startupName,
            onValueChange = { startupName = it },
            placeholder = "회사 이름"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = foundingDate,
            onValueChange = { foundingDate = it },
            placeholder = "설립일"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = businessNumber,
            onValueChange = { businessNumber = it },
            placeholder = "사업자 번호"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = representativeName,
            onValueChange = { representativeName = it },
            placeholder = "대표자명"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = address,
            onValueChange = { address = it },
            placeholder = "주소"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = homepageUrl,
            onValueChange = { homepageUrl = it },
            placeholder = "sns / 웹사이트"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = contactEmail,
            onValueChange = { contactEmail = it },
            placeholder = "이메일"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = intro,
            onValueChange = { intro = it },
            placeholder = "간단 기업 소개"
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            ProfileExposedDropdown(
                label = "운영 여부",
                selected = status,
                options = STATUS_TYPE,
                onSelect = { status = it },
                modifier = Modifier.weight(1f)
            )
            ProfileExposedDropdown(
                label = "규모",
                selected = scale,
                options = SCALE,
                onSelect = { scale = it },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            ProfileExposedDropdownMulti(
                label = "산업분야",
                selected = businessAreas,
                options = INDUSTRY_AREAS,
                onToggle = { key ->
                    businessAreas =
                        if (businessAreas.contains(key)) businessAreas - key
                        else businessAreas + key
                },
                modifier = Modifier.weight(1f)
            )
            ProfileExposedDropdown(
                label = "기업 분류",
                selected = legalType,
                options = LEGAL_TYPE,
                onSelect = { legalType = it },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "투자 정보",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = history,
            onValueChange = { history = it },
            placeholder = "대표자 약력"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = employeeCount,
            onValueChange = { employeeCount = it },
            placeholder = "고용 인원"
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "팀 및 구성원 정보",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = totalFunding,
            onValueChange = { totalFunding = it },
            placeholder = "누적유치금액"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = fundingRounds,
            onValueChange = { fundingRounds = it },
            placeholder = "투자유치건수"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = revenue,
            onValueChange = { revenue = it },
            placeholder = "매출액"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = profit,
            onValueChange = { profit = it },
            placeholder = "영업이익"
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // 원래 Create 화면이랑 완전 동일하게 두 개 넣었음
            ProfileExposedDropdown(
                label = "투자 단계",
                selected = investorStage,
                options = FUNDING_STAGE,
                onSelect = { investorStage = it },
                modifier = Modifier.weight(1f)
            )
            ProfileExposedDropdown(
                label = "투자 단계",
                selected = investorStage,
                options = FUNDING_STAGE,
                onSelect = { investorStage = it },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                onSubmit(
                    StartupProfileForm(
                        history = history,
                        startupName = startupName,
                        foundingDate = foundingDate,
                        scale = scale,
                        businessNumber = businessNumber,
                        representativeName = representativeName,
                        address = address,
                        homepageUrl = homepageUrl,
                        contactEmail = contactEmail,
                        intro = intro,
                        status = status,
                        businessAreas = businessAreas,
                        legalType = legalType,
                        employeeCount = employeeCount.toInt(),
                        totalFunding = totalFunding.toLong(),
                        fundingRounds = fundingRounds.toInt(),
                        revenue = revenue.toLong(),
                        profit = profit.toLong(),
                        investorStage = investorStage
                    )
                )
            },
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
            enabled =
                startupName.isNotBlank() &&
                        foundingDate.isNotBlank() &&
                        businessNumber.isNotBlank() &&
                        representativeName.isNotBlank() &&
                        history.isNotBlank() &&
                        address.isNotBlank() &&
                        homepageUrl.isNotBlank() &&
                        contactEmail.isNotBlank() &&
                        intro.isNotBlank() &&
                        status.isNotEmpty() &&
                        legalType.isNotEmpty() &&
                        businessAreas.isNotEmpty() &&
                        investorStage.isNotEmpty() &&
                        employeeCount.toIntOrNull() != null &&
                        totalFunding.toLongOrNull() != null &&
                        fundingRounds.toIntOrNull() != null &&
                        revenue.toLongOrNull() != null &&
                        profit.toLongOrNull() != null
        ) {
            Text(
                text = "완료", // 필요하면 "수정 완료"로 바꿔도 됨
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(Modifier.height(16.dp))
    }
}