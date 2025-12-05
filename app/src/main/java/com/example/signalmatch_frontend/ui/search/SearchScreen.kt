package com.example.signalmatch_frontend.ui.search

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.signalmatch_frontend.R
import com.example.signalmatch_frontend.ui.components.TabBar
import com.example.signalmatch_frontend.ui.components.button.SearchButton
import com.example.signalmatch_frontend.ui.components.card.SearchCard1
import com.example.signalmatch_frontend.ui.components.card.SearchCard2
import com.example.signalmatch_frontend.ui.components.card.SearchCard3
import com.example.signalmatch_frontend.viewmodel.BookmarkViewModel
import com.example.signalmatch_frontend.viewmodel.MypageEntryViewModel
import com.example.signalmatch_frontend.viewmodel.SearchViewModel
import androidx.compose.runtime.getValue
import com.example.signalmatch_frontend.data.model.response.BestStartupItem

@Composable
fun SearchScreen(
    navController: NavHostController,
    userId: Int,
    viewModel: SearchViewModel = hiltViewModel(),
    mypageViewModel: MypageEntryViewModel = hiltViewModel(),
    bookmarkViewModel: BookmarkViewModel = hiltViewModel()
) {
    val bookmarkUiState by bookmarkViewModel.uiState.collectAsState()

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val query = viewModel.query
    val startups = viewModel.startups
    val investors = viewModel.investors
    val isLoading = viewModel.isLoading
    val selectedAreas = viewModel.selectedAreas

    val bestStartups = viewModel.bestStartups
    val isSearched = viewModel.isSearched

    val hasResult = startups.isNotEmpty() || investors.isNotEmpty()

    LaunchedEffect(Unit) {
        bookmarkViewModel.loadBookmarks()
    }

    LaunchedEffect(bookmarkUiState.bookmark.size) {
        viewModel.loadBestStartups()
    }

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
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(innerPadding)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(Modifier.height(70.dp))

                OutlinedTextField(
                    value = query,
                    onValueChange = { viewModel.onQueryChange(it) },
                    modifier = Modifier
                        .height(60.dp)
                        .width(349.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(20.dp),
                    placeholder = {
                        Text(
                            "검색",
                            fontSize = 17.sp,
                            lineHeight = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFD9D9D9)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "검색",
                            tint = Color(0xFFADF1EB),
                            modifier = Modifier.clickable {
                                viewModel.resetSearch()
                            }
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color(0xFFAEF1EB),
                        unfocusedIndicatorColor = Color(0xFFAEF1EB),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black
                    ),
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Search),
                    keyboardActions = androidx.compose.foundation.text.KeyboardActions(
                        onSearch = {
                            viewModel.onSearch()
                        }
                    )
                )

                Spacer(Modifier.height(22.dp))

                Row {
                    SearchButton(
                        category = "AGRICULTURE_\nFORESTRY_FISHING",
                        fontsize = 9,
                        selected = selectedAreas.contains("AGRICULTURE_FORESTRY_FISHING"),
                        onClick = {
                            viewModel.toggleArea("AGRICULTURE_FORESTRY_FISHING")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "MINING",
                        fontsize = 9,
                        selected = selectedAreas.contains("MINING"),
                        onClick = {
                            viewModel.toggleArea("MINING")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "MANUFACTURING",
                        fontsize = 9,
                        selected = selectedAreas.contains("MANUFACTURING"),
                        onClick = {
                            viewModel.toggleArea("MANUFACTURING")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "ELECTRICITY_\nGAS_STEAM_AC",
                        fontsize = 9,
                        selected = selectedAreas.contains("ELECTRICITY_GAS_STEAM_AC"),
                        onClick = {
                            viewModel.toggleArea("ELECTRICITY_GAS_STEAM_AC")
                            viewModel.onSearch()
                        }
                    )
                }

                Spacer(Modifier.height(5.dp))

                Row {
                    SearchButton(
                        category = "WATER_SEWAGE_WASTE",
                        fontsize = 9,
                        selected = selectedAreas.contains("WATER_SEWAGE_WASTE"),
                        onClick = {
                            viewModel.toggleArea("WATER_SEWAGE_WASTE")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "CONSTRUCTION",
                        fontsize = 9,
                        selected = selectedAreas.contains("CONSTRUCTION"),
                        onClick = {
                            viewModel.toggleArea("CONSTRUCTION")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "WHOLESALE_RETAIL",
                        fontsize = 9,
                        selected = selectedAreas.contains("WHOLESALE_RETAIL"),
                        onClick = {
                            viewModel.toggleArea("WHOLESALE_RETAIL")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "TRANSPORTATION_\nWAREHOUSING",
                        fontsize = 9,
                        selected = selectedAreas.contains("TRANSPORTATION_WAREHOUSING"),
                        onClick = {
                            viewModel.toggleArea("TRANSPORTATION_WAREHOUSING")
                            viewModel.onSearch()
                        }
                    )
                }

                Spacer(Modifier.height(5.dp))

                Row {
                    SearchButton(
                        category = "ACCOMMODATION_\nFOOD",
                        fontsize = 9,
                        selected = selectedAreas.contains("ACCOMMODATION_FOOD"),
                        onClick = {
                            viewModel.toggleArea("ACCOMMODATION_FOOD")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "INFORMATION_\nCOMMUNICATION",
                        fontsize = 9,
                        selected = selectedAreas.contains("INFORMATION_COMMUNICATION"),
                        onClick = {
                            viewModel.toggleArea("INFORMATION_COMMUNICATION")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "FINANCE_INSURANCE",
                        fontsize = 9,
                        selected = selectedAreas.contains("FINANCE_INSURANCE"),
                        onClick = {
                            viewModel.toggleArea("FINANCE_INSURANCE")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "REAL_ESTATE",
                        fontsize = 9,
                        selected = selectedAreas.contains("REAL_ESTATE"),
                        onClick = {
                            viewModel.toggleArea("REAL_ESTATE")
                            viewModel.onSearch()
                        }
                    )
                }

                Spacer(Modifier.height(5.dp))

                Row {
                    SearchButton(
                        category = "PROFESSIONAL_\nSCIENTIFIC_TECH",
                        fontsize = 9,
                        selected = selectedAreas.contains("PROFESSIONAL_SCIENTIFIC_TECH"),
                        onClick = {
                            viewModel.toggleArea("PROFESSIONAL_SCIENTIFIC_TECH")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "BUSINESS_\nSUPPORT_RENTAL",
                        fontsize = 9,
                        selected = selectedAreas.contains("BUSINESS_SUPPORT_RENTAL"),
                        onClick = {
                            viewModel.toggleArea("BUSINESS_SUPPORT_RENTAL")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "PUBLIC_ADMIN_DEFENSE",
                        fontsize = 9,
                        selected = selectedAreas.contains("PUBLIC_ADMIN_DEFENSE"),
                        onClick = {
                            viewModel.toggleArea("PUBLIC_ADMIN_DEFENSE")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "EDUCATION",
                        fontsize = 9,
                        selected = selectedAreas.contains("EDUCATION"),
                        onClick = {
                            viewModel.toggleArea("EDUCATION")
                            viewModel.onSearch()
                        }
                    )
                }

                Spacer(Modifier.height(5.dp))

                Row {
                    SearchButton(
                        category = "HEALTH_SOCIAL_\nWORK",
                        fontsize = 9,
                        selected = selectedAreas.contains("HEALTH_SOCIAL_WORK"),
                        onClick = {
                            viewModel.toggleArea("HEALTH_SOCIAL_WORK")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "ARTS_SPORTS_\nRECREATION",
                        fontsize = 9,
                        selected = selectedAreas.contains("ARTS_SPORTS_RECREATION"),
                        onClick = {
                            viewModel.toggleArea("ARTS_SPORTS_RECREATION")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "ASSOCIATIONS_\nREPAIR_PERSONAL",
                        fontsize = 9,
                        selected = selectedAreas.contains("ASSOCIATIONS_REPAIR_PERSONAL"),
                        onClick = {
                            viewModel.toggleArea("ASSOCIATIONS_REPAIR_PERSONAL")
                            viewModel.onSearch()
                        }
                    )
                    Spacer(Modifier.width(6.dp))
                    SearchButton(
                        category = "HOUSEHOLD_EMPLOYMENT",
                        fontsize = 9,
                        selected = selectedAreas.contains("HOUSEHOLD_EMPLOYMENT"),
                        onClick = {
                            viewModel.toggleArea("HOUSEHOLD_EMPLOYMENT")
                            viewModel.onSearch()
                        }
                    )
                }

                Spacer(Modifier.height(44.dp))

                when {
                    isLoading -> {
                        CircularProgressIndicator()
                        Spacer(Modifier.height(16.dp))
                    }

                    isSearched && hasResult -> {

                        Spacer(Modifier.height(16.dp))

                        Text(
                            "START-UP",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                        startups.forEach { startup ->
                            val category =
                                if (startup.businessAreas.isNotEmpty())
                                    startup.businessAreas.joinToString(", ")
                                else
                                    startup.investorStages

                            var startupBookmarkCount by remember(startup.userId) { mutableStateOf(0) }

                            LaunchedEffect(startup.userId) {
                                viewModel.fetchStartupBookmarkCount(startup.userId) { count ->
                                    Log.d("SearchScreen", "startup ${startup.userId} bookmarkCount=$count")
                                    startupBookmarkCount = count
                                }
                            }

                            SearchCard3(
                                navController = navController,
                                name = startup.startupName,
                                category = category,
                                saveCount = startupBookmarkCount,
                                onClick = {
                                    navController.navigate("startup-info/${startup.userId}")
                                }
                            )
                            Spacer(Modifier.height(17.dp))
                        }

                        Text(
                            "INVESTOR",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                        investors.forEach { investor ->
                            val category =
                                if (investor.preferredAreas.isNotEmpty())
                                    investor.preferredAreas.joinToString(", ")
                                else
                                    investor.investorType

                            var investorBookmarkCount by remember(investor.userId) { mutableStateOf(0) }

                            LaunchedEffect(investor.userId) {
                                viewModel.fetchStartupBookmarkCount(investor.userId) { count ->
                                    Log.d("SearchScreen", "startup ${investor.userId} bookmarkCount=$count")
                                    investorBookmarkCount = count
                                }
                            }

                            SearchCard3(
                                navController = navController,
                                name = investor.investorName,
                                category = category,
                                saveCount = investorBookmarkCount,
                                onClick = {
                                    navController.navigate("investor-info/${investor.userId}")
                                }
                            )
                            Spacer(Modifier.height(17.dp))
                        }
                    }

                    isSearched && !hasResult -> {
                        Text(
                            text = "검색 결과가 없습니다.",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 32.dp)
                        )
                    }

                    else -> {
                        Text(
                            "이달의 랭킹🎖",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.offset(x = (-101).dp)
                        )

                        Spacer(Modifier.height(16.dp))

                        if (bestStartups.isEmpty()) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Text("등록된 start-up이 없습니다.")
                            }
                        } else {

                            val sortedBestStartups = bestStartups.sortedWith(
                                compareByDescending<BestStartupItem> { it.bookmarkCount }
                                    .thenBy { it.startupName }
                            )

                            sortedBestStartups.take(3).forEachIndexed { index, item ->
                                val iconRes = when (index) {
                                    0 -> R.drawable.ic_1st
                                    1 -> R.drawable.ic_2nd
                                    2 -> R.drawable.ic_3rd
                                    else -> R.drawable.ic_1st
                                }

                                SearchCard1(
                                    navController,
                                    iconRes,
                                    item.startupName,
                                    item.intro,
                                    saveCount = item.bookmarkCount,
                                    onClick = {
                                        navController.navigate("startup-info/${item.userId}")
                                    }
                                )
                                Spacer(Modifier.height(17.dp))
                            }

                            sortedBestStartups.drop(3).forEachIndexed { index, item ->
                                val rank = (index + 4).toString()

                                SearchCard2(
                                    navController,
                                    rank,
                                    item.startupName,
                                    item.intro,
                                    saveCount = item.bookmarkCount,
                                    onClick = {
                                        navController.navigate("startup-info/${item.userId}")
                                    }
                                )
                                Spacer(Modifier.height(17.dp))
                            }
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}


