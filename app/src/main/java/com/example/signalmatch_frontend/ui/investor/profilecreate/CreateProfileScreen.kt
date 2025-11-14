package com.example.signalmatch_frontend.ui.investor.profilecreate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import com.example.signalmatch_frontend.ui.components.dropdown.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.signalmatch_frontend.ui.components.etc.Logo
import com.example.signalmatch_frontend.ui.components.text.ProfileOutlinedTextField


private val INVESTOR_TYPES = listOf(
    "VC", "AC", "NIT", "AM", "THC", "PIA", "ASSOC", "FVC", "FI",
    "PUBLIC", "CORP", "ANGEL"
)

private val PREFERRED_SIZES = listOf(
    "UNDER_50M", "SIZE_50M_TO_100M", "SIZE_100M_TO_1B", "SIZE_1B_TO_10B", "OVER_10B"
)

private val PREFERRED_STAGES = listOf("SEED", "SERIES_A", "SERIES_B", "SERIES_C")

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

data class InvestorProfileForm(
    val investorName: String?,
    val phoneNumber: String,
    val contactEmail: String,
    val organizationName: String,
    val websiteUrl: String,
    val intro: String,
    val investorType: String?,
    val preferredInvestmentSize: String?,
    val preferredStages: Set<String>,
    val preferredAreas: Set<String>,
)

@Composable
fun CreateProfileScreen(
    modifier: Modifier = Modifier,
    onSubmit: (InvestorProfileForm) -> Unit = {}
) {
    var investorName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var organizationName by remember { mutableStateOf("") }
    var contactEmail by remember { mutableStateOf("") }
    var websiteUrl by remember { mutableStateOf("") }
    var intro by remember { mutableStateOf("") }
    var investorType by remember { mutableStateOf<String?>(null) }
    var preferredInvestmentSize by remember { mutableStateOf<String?>(null) }
    var preferredStages by remember { mutableStateOf(setOf<String>()) }
    var preferredAreas by remember { mutableStateOf(setOf<String>()) }

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

        ProfileOutlinedTextField(value = investorName, onValueChange = { investorName = it }, placeholder = "이름")
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(value = phoneNumber, onValueChange = { phoneNumber = it }, placeholder = "연락처")
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(value = organizationName, onValueChange = { organizationName = it }, placeholder = "소속")
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(value = contactEmail, onValueChange = { contactEmail = it }, placeholder = "이메일")
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(value = websiteUrl, onValueChange = { websiteUrl = it }, placeholder = "sns / 웹사이트")
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(value = intro, onValueChange = { intro = it }, placeholder = "간단 자기소개")
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            ProfileExposedDropdown(
                label = "투자자 유형",
                selected = investorType,
                options = INVESTOR_TYPES,
                onSelect = { investorType = it },
                modifier = Modifier.weight(1f)
            )
            ProfileExposedDropdown(
                label = "투자 희망 규모",
                selected = preferredInvestmentSize,
                options = PREFERRED_SIZES,
                onSelect = { preferredInvestmentSize = it },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            ProfileExposedDropdownMulti(
                label = "선호 산업분야",
                selected = preferredAreas,
                options = INDUSTRY_AREAS,
                onToggle = { key ->
                    preferredAreas = if (preferredAreas.contains(key)) preferredAreas - key else preferredAreas + key
                },
                modifier = Modifier.weight(1f)
            )

            ProfileExposedDropdownMulti(
                label = "선호 투자 단계",
                selected = preferredStages,
                options = PREFERRED_STAGES,
                onToggle = { key ->
                    preferredStages = if (preferredStages.contains(key)) preferredStages - key else preferredStages + key
                },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(12.dp))
        Button(
            onClick = {
                onSubmit(
                    InvestorProfileForm(
                        investorName = investorName,
                        contactEmail = contactEmail,
                        organizationName = organizationName,
                        phoneNumber = phoneNumber,
                        websiteUrl = websiteUrl,
                        intro = intro,
                        investorType = investorType,
                        preferredInvestmentSize = preferredInvestmentSize,
                        preferredStages = preferredStages,
                        preferredAreas = preferredAreas
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFAEF1EB),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFFAEF1EB).copy(alpha = 0.4f), // 선택
                disabledContentColor = Color.White.copy(alpha = 0.6f)          // 선택
            ),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            enabled =
                investorName.isNotBlank() &&
                        phoneNumber.isNotBlank() &&
                        organizationName.isNotBlank() &&
                        contactEmail.isNotBlank() &&
                        websiteUrl.isNotBlank() &&
                        intro.isNotBlank() &&
                        investorType != null &&
                        preferredInvestmentSize != null &&
                        preferredStages.isNotEmpty() &&
                        preferredAreas.isNotEmpty()
        ) {
            Text(
                text = "완료",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun ProfilePreview(){
    CreateProfileScreen()
}

