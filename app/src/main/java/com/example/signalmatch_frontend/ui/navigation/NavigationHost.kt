package com.example.signalmatch_frontend.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.signalmatch_frontend.ui.landing.LandingScreen
import com.example.signalmatch_frontend.ui.login.LoginScreen
import com.example.signalmatch_frontend.ui.home.HomeScreen
import com.example.signalmatch_frontend.ui.investor.mypage.InvestorMypageRoute
import com.example.signalmatch_frontend.ui.investor.profilecreate.InvestorCreateProfileRoute
import com.example.signalmatch_frontend.ui.mypage.FAQScreen
import com.example.signalmatch_frontend.ui.mypage.ManageAccountScreen
import com.example.signalmatch_frontend.ui.mypage.bookmark_list.BookmarkRoute
import com.example.signalmatch_frontend.ui.mypage.matching_list.MatchingListRoute
import com.example.signalmatch_frontend.ui.search.SearchScreen
import com.example.signalmatch_frontend.ui.signup.SignupRoleScreen
import com.example.signalmatch_frontend.ui.signup.SignupScreen
import com.example.signalmatch_frontend.ui.startup.mypage.StartupMypageRoute
import com.example.signalmatch_frontend.ui.post.PostLoginRoute
import com.example.signalmatch_frontend.ui.startup.profilecreate.StartupCreateProfileRoute

@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {


        composable("landing") { LandingScreen(navController) }
        composable("home") { HomeScreen(navController) }

        //회원가입
        composable("signup-role") { SignupRoleScreen(navController) }
        composable(route = "signup/{userRole}") { backStackEntry ->
            val userRole = backStackEntry.arguments?.getString("userRole") ?: "INVESTOR"
            SignupScreen(
                navController = navController,
                userRole = userRole
            )
        }

        //로그인
        composable("login") {
            LoginScreen(
                navController = navController,
                onLoginSuccess = { userId, userRole ->
                    navController.navigate("post-login/$userId/$userRole") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }


        //분기 작업
        composable(
            route = "post-login/{userId}/{userRole}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType },
                navArgument("userRole") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: -1
            val userRole = backStackEntry.arguments?.getString("userRole") ?: ""

            PostLoginRoute(
                navController = navController,
                userId = userId,
                userRole = userRole
            )
        }

        //프로필 생성
        composable("investor-create profile") { InvestorCreateProfileRoute(navController) }
        composable("startup-create profile") { StartupCreateProfileRoute(navController) }

        //검색
        composable("search") { SearchScreen(navController) }

        //마이페이지
        composable("investor-mypage") { InvestorMypageRoute(navController) }
        composable("startup-mypage") { StartupMypageRoute(navController) }
        composable("bookmark") { BookmarkRoute(navController) }
        composable("matching-list") { MatchingListRoute(navController) }
        composable("manage account") { ManageAccountScreen(navController) }
        composable("FAQ") { FAQScreen(navController) }
        // composable("chat") { ChatListScreen(navController) }

    }
}
