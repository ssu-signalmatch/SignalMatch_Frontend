package com.example.signalmatch_frontend.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.api.SearchApi
import com.example.signalmatch_frontend.data.model.response.BestStartupItem
import com.example.signalmatch_frontend.data.model.response.InvestorSearchDto
import com.example.signalmatch_frontend.data.model.response.StartupSearchDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private val INDUSTRY_AREAS = listOf(
    "AGRICULTURE_FORESTRY_FISHING", "MINING", "MANUFACTURING",
    "ELECTRICITY_GAS_STEAM_AC", "WATER_SEWAGE_WASTE", "CONSTRUCTION",
    "WHOLESALE_RETAIL", "TRANSPORTATION_WAREHOUSING", "ACCOMMODATION_FOOD",
    "INFORMATION_COMMUNICATION", "FINANCE_INSURANCE", "REAL_ESTATE",
    "PROFESSIONAL_SCIENTIFIC_TECH", "BUSINESS_SUPPORT_RENTAL",
    "PUBLIC_ADMIN_DEFENSE", "EDUCATION", "HEALTH_SOCIAL_WORK",
    "ARTS_SPORTS_RECREATION", "ASSOCIATIONS_REPAIR_PERSONAL",
    "HOUSEHOLD_EMPLOYMENT", "INTERNATIONAL_FOREIGN_ORG"
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchApi: SearchApi
) : ViewModel() {

    var query by mutableStateOf("")
        private set

    var selectedAreas by mutableStateOf<List<String>>(INDUSTRY_AREAS)
        private set

    var startups by mutableStateOf<List<StartupSearchDto>>(emptyList())
        private set

    var investors by mutableStateOf<List<InvestorSearchDto>>(emptyList())
        private set

    var bestStartups by mutableStateOf<List<BestStartupItem>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var isSearched by mutableStateOf(false)
        private set

    init {
        loadBestStartups()
    }

    fun onQueryChange(newQuery: String) {
        query = newQuery
    }

    fun toggleArea(area: String) {
        selectedAreas =
            if (selectedAreas.contains(area)) selectedAreas - area
            else selectedAreas + area
    }

    fun loadBestStartups() {
        viewModelScope.launch {
            try {
                isLoading = true
                val res = searchApi.getBestStartups()
                bestStartups = if (res.success) res.data else emptyList()
            } catch (e: Exception) {
                Log.e("SearchViewModel", "getBestStartups error: ${e.message}", e)
                bestStartups = emptyList()
            } finally {
                isLoading = false
            }
        }
    }

    fun getBookmarkCountForUser(userId: Int): Int {
        return bestStartups
            .firstOrNull { it.userId == userId }
            ?.bookmarkCount
            ?: 0
    }

    fun resetSearch() {
        query = ""
        startups = emptyList()
        investors = emptyList()
        isSearched = false
    }

    fun onSearch() {
        viewModelScope.launch {
            Log.d("SearchViewModel", "onSearch() called. query='$query', areas=$selectedAreas")

            try {
                isLoading = true
                isSearched = true

                val res = searchApi.search(
                    keyword = query,
                    areas = selectedAreas.takeIf { it.isNotEmpty() },
                    page = 0,
                    size = 10,
                    sort = emptyList()
                )

                Log.d(
                    "SearchViewModel",
                    "search response success=${res.success}, " +
                            "startupCount=${res.data.startups.size}, " +
                            "investorCount=${res.data.investors.size}"
                )

                if (res.success) {
                    startups = res.data.startups
                    investors = res.data.investors

                    if (startups.isNotEmpty()) {
                        Log.d("SearchViewModel", "first startup userId=${startups.first().userId}")
                    }
                    if (investors.isNotEmpty()) {
                        Log.d("SearchViewModel", "first investor userId=${investors.first().userId}")
                    }
                } else {
                    Log.e("SearchViewModel", "search failed in API: ${res.message}")
                    startups = emptyList()
                    investors = emptyList()
                }

            } catch (e: Exception) {
                Log.e("SearchViewModel", "search error: ${e.message}", e)
                startups = emptyList()
                investors = emptyList()
            } finally {
                isLoading = false
            }
        }
    }
}
