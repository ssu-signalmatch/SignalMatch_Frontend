package com.example.signalmatch_frontend.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.signalmatch_frontend.ui.components.TabBar
import com.example.signalmatch_frontend.ui.navigation.NavigationHost

@Composable
fun RootScaffold(
    startDestination: String = "landing"
) {
    val navController = rememberNavController()
    val tabRoutes = listOf("home", "search", "matching", "message", "mypage")
    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in tabRoutes) {
                TabBar(navController)
            }
        }
    ) { innerPadding ->
        Surface(Modifier.padding(innerPadding)) {
            NavigationHost(
                navController = navController,
                startDestination = startDestination
            )
        }
    }
}
