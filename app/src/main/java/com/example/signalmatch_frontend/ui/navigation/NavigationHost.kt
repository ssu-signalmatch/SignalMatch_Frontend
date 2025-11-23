package com.example.signalmatch_frontend.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.signalmatch_frontend.ui.chat.list.ChatListScreen
import com.example.signalmatch_frontend.ui.landing.LandingScreen
import com.example.signalmatch_frontend.ui.login.LoginScreen
import com.example.signalmatch_frontend.ui.home.HomeScreen


@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("landing") { LandingScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("search")  { /* SearchScreen(navController) */ }
        composable("matching")   { /* MatchingScreen(navController) */ }
        composable("message") { ChatListScreen(navController) }
        composable("mypage")  { /* MyPageScreen(navController) */ }
    }
}
