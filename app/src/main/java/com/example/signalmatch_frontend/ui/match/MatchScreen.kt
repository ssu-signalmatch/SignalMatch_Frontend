package com.example.signalmatch_frontend.ui.match

import android.widget.Toast
import androidx.activity.result.launch
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.signalmatch_frontend.ui.components.TabBar
import com.example.signalmatch_frontend.ui.components.etc.Logo
import com.example.signalmatch_frontend.viewmodel.MypageEntryViewModel
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

@Preview
@Composable
fun MatchScreenPreview () {
    MatchScreen(
        navController = rememberNavController(),
        userId = 1
    )
}

@Preview(showBackground = true)
@Composable
fun MatchingCardPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SwipeableMatchingCard()
    }
}


@Composable
fun MatchScreen (
    navController: NavController,
    userId: Int,
    mypageViewModel: MypageEntryViewModel = hiltViewModel(),
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
    ) {innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Logo(size = 105.dp)
            Spacer(modifier = Modifier.height(48.dp))
            SwipeableMatchingCard()
        }
    }
}

@Composable
fun SwipeableMatchingCard() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val swipeThreshold = screenWidth * 0.25f

    var offsetX by remember { mutableFloatStateOf(0f) }
    var swipedOut by remember { mutableStateOf(false) }


    val animatedOffsetX by animateFloatAsState(
        targetValue = if (swipedOut) {
            if (offsetX > 0) screenWidth.value * 2 else -screenWidth.value * 2
        } else {
            offsetX
        },
        animationSpec = tween(durationMillis = 200),
        label = "swipeOutAnimation",
        finishedListener = {
            if (swipedOut) {
                offsetX = 0f
                swipedOut = false
                // TODO: Logic
                Toast.makeText(context, "Next Card!", Toast.LENGTH_SHORT).show()
            }
        }
    )

    MatchingCard(
        modifier = Modifier
            .offset { IntOffset(animatedOffsetX.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        scope.launch {
                            if (abs(offsetX) < swipeThreshold.value) offsetX = 0f
                        }
                    },
                    onHorizontalDrag = { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount

                        if (abs(offsetX) > swipeThreshold.value) {
                            if (!swipedOut) {
                                swipedOut = true
                                if (offsetX > 0) {
                                    Toast.makeText(context, "Like!", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(context, "Nope!", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                )
            }
    )
}

@Composable
fun MatchingCard(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF7F7F7))
            .border(
                1.dp,
                Color(0xFFE0E0E0),
                RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        // 1. 상단 프로필 영역
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFD9D9D9))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "코끼리 연구소 8",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "핀테크 • seed",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 2. 소개 문구
        Text(
            text = "\"Z세대를 위한 직관적이고 재미있는 자산 관리 앱을 만듭니다.\"",
            fontSize = 16.sp,
            color = Color.DarkGray,
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 3. 주요 지표 (매출, 성장률, MAU)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min), // 자식 요소들의 높이를 맞추기 위해 사용
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoItem(value = "5.0억원", label = "매출")
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(Color.LightGray)
            )
            InfoItem(value = "120%", label = "성장률(전월 대비)")
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(Color.LightGray)
            )
            InfoItem(value = "6,744 명", label = "MAU")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 4. 상세 정보
        DetailRow(icon = Icons.Outlined.MailOutline, text = "누적 투자 유치 금액 13억")
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DetailRow(icon = Icons.Default.Face, text = "대표 고끼리 외 팀원 9명")
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Likes",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "+ 461", color = Color.Gray, fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        DetailRow(icon = Icons.Default.Search, text = "IR 자료 보기")
    }
}

@Composable
private fun InfoItem(value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp, color = Color.Gray, textAlign = TextAlign.Center)
    }
}

@Composable
private fun DetailRow(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, fontSize = 14.sp, color = Color.DarkGray)
    }
}