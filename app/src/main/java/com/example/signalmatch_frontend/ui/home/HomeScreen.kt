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


private enum class Tab { Recommend, News }

@Composable
fun HomeCard(
    modifier: Modifier = Modifier
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
                    Tab.Recommend -> RecommendCompaniesContent()
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
        modifier = Modifier.clickable(onClick = onClick)
    )
}


@Composable
private fun RecommendCompaniesContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "추천 기업 리스트 자리",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF757575)
        )
    }
}

@Composable
private fun RecentNewsContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "최근 뉴스 리스트 자리",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF757575)
        )
    }
}

@Composable
fun HomeScreen(
    navController: NavHostController,
    userId: Int,
    mypageViewModel: MypageEntryViewModel = hiltViewModel()
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
            HomeCard()
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