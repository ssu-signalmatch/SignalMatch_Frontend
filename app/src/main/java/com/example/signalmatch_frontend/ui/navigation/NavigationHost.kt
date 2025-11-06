package com.example.signalmatch_frontend.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.signalmatch_frontend.ui.landing.LandingScreen
import com.example.signalmatch_frontend.ui.login.LoginScreen


@Composable
fun NavigationHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "landing"
    ) {
        composable("landing") { LandingScreen(navController) }
        composable("login") { LoginScreen(navController) }
    }
}
