package com.example.signalmatch_frontend.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.signalmatch_frontend.ui.chat.list.ChatListRoute
import com.example.signalmatch_frontend.ui.chat.list.ChatListScreen
import com.example.signalmatch_frontend.ui.chat.room.ChatRoomRoute
import com.example.signalmatch_frontend.ui.chat.room.ChatRoomScreen
import com.example.signalmatch_frontend.ui.landing.LandingScreen
import com.example.signalmatch_frontend.ui.login.LoginScreen
import com.example.signalmatch_frontend.ui.home.HomeScreen
import com.example.signalmatch_frontend.ui.info.InfoIRRoute
import com.example.signalmatch_frontend.ui.info.InvestorInfoRoute
import com.example.signalmatch_frontend.ui.info.StartupInfoRoute
import com.example.signalmatch_frontend.ui.investor.mypage.InvestorMypageRoute
import com.example.signalmatch_frontend.ui.investor.profilecreate.InvestorCreateProfileRoute
import com.example.signalmatch_frontend.ui.investor.profiledetail.InvestorProfileDetailRoute
import com.example.signalmatch_frontend.ui.investor.profileedit.InvestorEditProfileRoute
import com.example.signalmatch_frontend.ui.ir.IRRoute
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
import com.example.signalmatch_frontend.ui.startup.profiledetail.StartupProfileDetailRoute
import com.example.signalmatch_frontend.ui.startup.profileedit.StartupEditProfileRoute

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
        composable(
            route = "investor-create profile/{userId}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: -1

            InvestorCreateProfileRoute(
                navController = navController,
                userId = userId
            )
        }
        composable(
            route = "startup-create profile/{userId}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: -1

            StartupCreateProfileRoute(
                navController = navController,
                userId = userId
            )
        }

        // 홈
        composable(
            route = "home/{userId}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: -1

            HomeScreen(
                navController = navController,
                userId = userId
            )
        }

        // 검색
        composable(
            route = "search/{userId}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: -1

            SearchScreen(
                navController = navController,
                userId = userId
            )
        }

        //검색 - 상세 조회
        composable(
            route = "startup-info/{userId}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: -1

            StartupInfoRoute(
                navController = navController,
                userId = userId
            )
        }

        composable(
            route = "investor-info/{userId}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: -1

            InvestorInfoRoute(
                navController = navController,
                userId = userId
            )
        }

        composable(
            route = "info-ir/{startupId}",
            arguments = listOf(
                navArgument("startupId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val startupId = backStackEntry.arguments?.getInt("startupId") ?: -1
            InfoIRRoute(
                navController = navController,
                startupId = startupId
            )
        }

        //마이페이지
        composable("investor-mypage/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")!!.toInt()
            InvestorMypageRoute(navController = navController, userId = userId)
        }

        composable("startup-mypage/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")!!.toInt()
            StartupMypageRoute(navController = navController, userId = userId)
        }

        //프로필 상세조회
        composable(
            route = "investor-profile detail/{userId}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: -1

            InvestorProfileDetailRoute(
                navController = navController,
                userId = userId
            )
        }
        composable(
            route = "startup-profile detail/{userId}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: -1

            StartupProfileDetailRoute(
                navController = navController,
                userId = userId
            )
        }

        //프로필 수정
        composable("investor-edit profile/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")!!.toInt()
            InvestorEditProfileRoute(navController = navController, userId = userId)
        }
        composable("startup-edit profile/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")!!.toInt()
            StartupEditProfileRoute(navController = navController, userId = userId)
        }

        //IR
        composable(
            route = "ir/{startupId}",
            arguments = listOf(
                navArgument("startupId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val startupId = backStackEntry.arguments?.getInt("startupId") ?: -1
            IRRoute(
                navController = navController,
                startupId = startupId
            )
        }

        composable(
            route = "chat-list/{userId}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: -1

            ChatListRoute(
                navController = navController,
                userId = userId
            )
        }

        composable(
            route = "chat-room/{userId}/{roomId}/{roomName}",
            arguments = listOf(
                navArgument("userId") { type = NavType.LongType },
                navArgument("roomId") { type = NavType.LongType },
                navArgument("roomName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getLong("userId") ?: -1
            val roomId = backStackEntry.arguments?.getLong("roomId") ?: -1
            val roomName = backStackEntry.arguments?.getString("roomName") ?: ""

            ChatRoomRoute(
                navController = navController,
                userId = userId,
                roomId = roomId,
                roomName = roomName
            )
        }


        composable("bookmark") { BookmarkRoute(navController) }
        composable("matching-list") { MatchingListRoute(navController) }
        composable("manage account") { ManageAccountScreen(navController) }
        composable("FAQ") { FAQScreen(navController) }

    }
}
