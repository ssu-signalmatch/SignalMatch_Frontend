package com.example.signalmatch_frontend.ui.investor.profileedit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

import com.example.signalmatch_frontend.ui.components.etc.Logo
import com.example.signalmatch_frontend.ui.components.text.ProfileOutlinedTextField
import com.example.signalmatch_frontend.ui.components.dropdown.ProfileExposedDropdown
import com.example.signalmatch_frontend.ui.components.dropdown.ProfileExposedDropdownMulti

data class InvestorProfileForm(
    val investorName: String,
    val phoneNumber: String,
    val contactEmail: String,
    val organizationName: String,
    val websiteUrl: String,
    val intro: String,
    val investorType: String,
    val preferredInvestmentSize: String,
    val preferredStages: Set<String>,
    val preferredAreas: Set<String>,
)

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

@Composable
fun InvestorProfileScreen(
    modifier: Modifier = Modifier,
    initialForm: InvestorProfileForm? = null,
    onSubmit: (InvestorProfileForm) -> Unit = {}
) {
    var investorName by remember(initialForm) { mutableStateOf(initialForm?.investorName ?: "") }
    var phoneNumber by remember(initialForm) { mutableStateOf(initialForm?.phoneNumber ?: "") }
    var organizationName by remember(initialForm) { mutableStateOf(initialForm?.organizationName ?: "") }
    var contactEmail by remember(initialForm) { mutableStateOf(initialForm?.contactEmail ?: "") }
    var websiteUrl by remember(initialForm) { mutableStateOf(initialForm?.websiteUrl ?: "") }
    var intro by remember(initialForm) { mutableStateOf(initialForm?.intro ?: "") }
    var investorType by remember(initialForm) { mutableStateOf(initialForm?.investorType ?: "") }
    var preferredInvestmentSize by remember(initialForm) { mutableStateOf(initialForm?.preferredInvestmentSize ?: "") }
    var preferredStages by remember(initialForm) { mutableStateOf(initialForm?.preferredStages ?: emptySet()) }
    var preferredAreas by remember(initialForm) { mutableStateOf(initialForm?.preferredAreas ?: emptySet()) }

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
        Spacer(modifier = Modifier.height(40.dp))
        Logo(98.dp)
        Spacer(modifier = Modifier.height(7.dp))

        ProfileOutlinedTextField(
            value = investorName,
            onValueChange = { investorName = it },
            placeholder = "이름"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            placeholder = "연락처"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = organizationName,
            onValueChange = { organizationName = it },
            placeholder = "소속"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = contactEmail,
            onValueChange = { contactEmail = it },
            placeholder = "이메일"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = websiteUrl,
            onValueChange = { websiteUrl = it },
            placeholder = "sns / 웹사이트"
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileOutlinedTextField(
            value = intro,
            onValueChange = { intro = it },
            placeholder = "간단 자기소개"
        )
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
                    preferredAreas = if (preferredAreas.contains(key)) {
                        preferredAreas - key
                    } else {
                        preferredAreas + key
                    }
                },
                modifier = Modifier.weight(1f)
            )

            ProfileExposedDropdownMulti(
                label = "선호 투자 단계",
                selected = preferredStages,
                options = PREFERRED_STAGES,
                onToggle = { key ->
                    preferredStages = if (preferredStages.contains(key)) {
                        preferredStages - key
                    } else {
                        preferredStages + key
                    }
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
                disabledContainerColor = Color(0xFFAEF1EB).copy(alpha = 0.4f),
                disabledContentColor = Color.White.copy(alpha = 0.6f)
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
                        investorType.isNotBlank() &&
                        preferredInvestmentSize.isNotBlank() &&
                        preferredStages.isNotEmpty() &&
                        preferredAreas.isNotEmpty()
        ) {
            Text(
                text = if (initialForm == null) "완료" else "수정 완료",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(Modifier.height(16.dp))
    }
}
