package com.example.signalmatch_frontend.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.signalmatch_frontend.R

@Composable
fun TabBar(
    navController: NavController,
    userId: Int,
    onMypageClick: () -> Unit
) {
    val tabs = listOf(
        "home" to R.drawable.ic_home,
        "search" to R.drawable.ic_search,
        "matching" to R.drawable.ic_matching,
        "message" to R.drawable.ic_message,
        "mypage" to R.drawable.ic_mypage
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Surface(
        color = Color(0xFFAEF1EB),
        modifier = Modifier
            .height(102.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            tabs.forEach { (route, iconRes) ->

                // 👇 탭별 실제 네비게이션 route 결정
                val navigateRoute = when (route) {
                    "home"     -> "home/$userId"
                    "search"   -> "search/$userId"
                    "matching" -> "matching/$userId"
                    else       -> route
                }

                val selected = currentRoute == navigateRoute

                IconButton(
                    onClick = {
                        if (route == "mypage") {
                            onMypageClick()
                        } else if (!selected) {
                            navController.navigate(navigateRoute) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(iconRes),
                        contentDescription = route,
                        tint = if (selected) Color.White else Color.White.copy(alpha = 0.6f),
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
}
