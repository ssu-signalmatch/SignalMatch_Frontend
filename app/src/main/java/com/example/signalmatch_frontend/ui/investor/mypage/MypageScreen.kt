package com.example.signalmatch_frontend.ui.investor.mypage

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signalmatch_frontend.ui.components.TabBar
import com.example.signalmatch_frontend.ui.components.card.MypageCard
import com.example.signalmatch_frontend.ui.components.card.MypageCard2
import com.example.signalmatch_frontend.ui.components.etc.Logo
import com.example.signalmatch_frontend.ui.components.card.InvestorProfileCard
import com.example.signalmatch_frontend.viewmodel.MypageEntryViewModel

@Composable
fun InvestorMypageScreen(
    navController: NavController,
    userId: Int,
    uiState: InvestorMypageUiState,
    mypageViewModel: MypageEntryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    LaunchedEffect(Unit) {
        mypageViewModel.loadProfileImage()
    }

    val profileImageUrl by mypageViewModel.profileImageUrl.collectAsState()

    Scaffold(
        bottomBar = {
            TabBar(
                navController = navController,
                userId = userId,
                onMypageClick = {
                    mypageViewModel.openMypage(
                        navController = navController,
                        userId = userId,
                        onError = { msg ->
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        when (uiState) {
            is InvestorMypageUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is InvestorMypageUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.message,
                        color = Color.Red,
                        fontSize = 16.sp
                    )
                }
            }

            is InvestorMypageUiState.Success -> {
                val data = uiState.data

                val investorName = data.investorName
                val organizationName = data.organizationName
                val displayImage = selectedImageUri?.toString() ?: profileImageUrl

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.height(63.dp))
                    Logo(105.dp)

                    InvestorProfileCard(
                        navController,
                        displayImage,
                        investorName,
                        organizationName,
                        userId
                    )
                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        "저장/매칭 관리",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.offset((-124).dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    MypageCard2(navController, "즐겨찾기 관리", "매칭 리스트 관리", "bookmark", "matching-list")

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        "관리/기타",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.offset((-142).dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    MypageCard2(navController, "고객센터 / FAQ", "계정관리", "FAQ", "manage account")
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
