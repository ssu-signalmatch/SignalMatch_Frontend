package com.example.signalmatch_frontend.ui.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.signalmatch_frontend.ui.components.etc.Logo
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.signalmatch_frontend.ui.components.TabBar
import com.example.signalmatch_frontend.viewmodel.MypageEntryViewModel
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.signalmatch_frontend.data.model.response.SearchResponse
import com.example.signalmatch_frontend.ui.chat.list.ChatListUiState
import com.example.signalmatch_frontend.viewmodel.HomeViewModel


private enum class Tab { Recommend, News }

@Composable
fun HomeCard(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    userId: Int,
    viewModel: HomeViewModel
) {
    var selected by remember { mutableStateOf(Tab.Recommend) }
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 30.dp)
        ) {
            TabText(
                text = "추천 기업",
                selected = selected == Tab.Recommend,
                onClick = { selected = Tab.Recommend }
            )
            Spacer(modifier = Modifier.width(16.dp))
            TabText(
                text = "최근 뉴스",
                selected = selected == Tab.News,
                onClick = { selected = Tab.News }
            )
        }

        Spacer(Modifier.height(12.dp))

        Card(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .height(473.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFAFAFA)
            )
        ) {
            Crossfade(targetState = selected, label = "tabContent") { tab ->
                when (tab) {
                    Tab.Recommend -> RecommendCompaniesContent(
                        uiState = uiState,
                        userId = userId,
                        viewModel = viewModel
                    )
                    Tab.News -> RecentNewsContent()
                }
            }
        }
    }
}


@Composable
private fun TabText(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
        color = if (selected) Color.Black else Color(0xFFBDBDBD),
        modifier = Modifier.clickable(
            enabled = true,
            onClick = onClick
        )
    )
}


@Composable
private fun RecommendCompaniesContent(
    uiState: HomeUiState,
    userId: Int,
    viewModel: HomeViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is HomeUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is HomeUiState.Error -> {
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

            is HomeUiState.Success -> {
                var isInvestor = false;
                LaunchedEffect(userId) {
                    viewModel.loadInvestor(userId) {
                        isInvestor = it
                    }
                }

                HomeSuggestTabContainer (
                    isInvestor = isInvestor,
                    searchResponse = uiState.data
                )

            }
        }

        /*
        Text(
            text = "추천 기업 리스트 자리",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF757575)
        )*/
    }
}

@Composable
private fun RecentNewsContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HomeNewsTabContainer(
            data = arrayListOf(
                HomeNewItem(1, "정부, ‘나눠먹기식 中企 지원’ 손본다 [AI 프리즘*스타트업 창업자 뉴스]", "서울 경제 | 우승호 기자·박세은 인턴기자", "2025.12.08 06:27:38"),
                HomeNewItem(2, "퀄컴, 아시아·태평양 AI 스타트업 지원", "인더스트리 뉴스 | 한원석 기자", "2025.12.07 20:27:00"),
                HomeNewItem(3, "스타트업 투자, 돈되는 뷰티만 '온기'", "이데일리 | 김응태 기자", "2025-12-08 08:37:21"),
                HomeNewItem(4, "오픈AI, AI 모델 훈련 모니터링 스타트업 ‘넵튠AI’ 인수", "KBS 뉴스 | 이광열 기자", "2025.12.04 05:11:00"),
                HomeNewItem(5, "[게시판] 산업은행 '넥스트원 부산 3기 데모데이'…스타트업 지원", "연합 뉴스 | 배영 기자", "2025-12-02 15:48:00"),
            )
        )

        /*
        Text(
            text = "최근 뉴스 리스트 자리",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF757575)
        )
         */
    }
}

@Composable
fun HomeScreen(
    navController: NavController,
    userId: Int,
    uiState: HomeUiState,
    mypageViewModel: MypageEntryViewModel = hiltViewModel(),
    viewModel: HomeViewModel = hiltViewModel()
) {

    val context = LocalContext.current

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(90.dp))
            Logo(126.dp)
            Spacer(modifier = Modifier.height(14.dp))
            HomeCard(
                uiState = uiState,
                userId = userId,
                viewModel = viewModel
            )
        }
    }
}

/*
@Preview
@Composable
fun HomePreview(){
    val navController = rememberNavController()
    HomeScreen(
        navController = navController,
        userId = 40
    )
}*/