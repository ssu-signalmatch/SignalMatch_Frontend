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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.signalmatch_frontend.R

@Composable
fun TabBar(navController: NavHostController) {
    val tabs = listOf(
        "home" to R.drawable.ic_home,
        "search" to R.drawable.ic_search,
        "matching" to R.drawable.ic_matching,
        "message" to R.drawable.ic_message,
        "mypage" to R.drawable.ic_mypage
    )

    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route

    Surface(
        color = Color(0xFFAEF1EB),
        modifier = Modifier
            .fillMaxWidth()
            .height(102.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { (route, iconRes) ->
                val selected = currentRoute == route
                IconButton(
                    onClick = {
                        if (!selected) {
                            navController.navigate(route) {
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
