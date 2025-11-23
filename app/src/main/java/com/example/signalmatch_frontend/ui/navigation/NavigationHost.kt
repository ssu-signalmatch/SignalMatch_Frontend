package com.example.signalmatch_frontend.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.signalmatch_frontend.ui.chat.list.ChatListScreen
import com.example.signalmatch_frontend.ui.landing.LandingScreen
import com.example.signalmatch_frontend.ui.login.LoginScreen
import com.example.signalmatch_frontend.ui.home.HomeScreen
import com.example.signalmatch_frontend.ui.investor.profilecreate.InvestorCreateProfileRoute
import com.example.signalmatch_frontend.ui.signup.SignupRoleScreen
import com.example.signalmatch_frontend.ui.signup.SignupScreen
import com.example.signalmatch_frontend.ui.startup.profilecreate.StartupCreateProfileRoute
import com.example.signalmatch_frontend.ui.startup.mypage.StartupMypageScreen


@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("investor_create_profile") { InvestorCreateProfileRoute(navController) }
        composable("startup_create_profile") { StartupCreateProfileRoute(navController) }
        composable("landing") { LandingScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable(
            route = "signup/{userRole}",
            arguments = listOf(
                navArgument("userRole") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val role = backStackEntry.arguments?.getString("userRole") ?: "INVESTOR"
            SignupScreen(
                navController = navController,
                userRole = role
            )
        }
        composable("signup") { SignupRoleScreen(navController) }
        composable("search")  { /* SearchScreen(navController) */ }
        composable("matching")   { /* MatchingScreen(navController) */ }
        composable("message") { ChatListScreen(navController) }
        composable("mypage-startup")  { StartupMypageScreen(navController) }
        composable("bookmarks") { /*BookmarksScreen(navController)*/ }
    }
}
