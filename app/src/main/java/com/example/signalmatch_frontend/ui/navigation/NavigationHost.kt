package com.example.signalmatch_frontend.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.signalmatch_frontend.ui.landing.LandingScreen
import com.example.signalmatch_frontend.ui.login.LoginScreen
import com.example.signalmatch_frontend.ui.home.HomeScreen
import com.example.signalmatch_frontend.ui.investor.profilecreate.InvestorCreateProfileRoute
import com.example.signalmatch_frontend.ui.startup.profilecreate.StartupCreateProfileRoute


@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("investor_create") { InvestorCreateProfileRoute(navController) }
        composable("startup_create") { StartupCreateProfileRoute(navController) }
        composable("landing") { LandingScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("search")  { /* SearchScreen(navController) */ }
        composable("matching")   { /* MatchingScreen(navController) */ }
        composable("message") { /* MessageScreen(navController) */ }
        composable("mypage")  { /* MyPageScreen(navController) */ }
    }
}
