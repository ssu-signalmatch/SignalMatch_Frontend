package com.example.signalmatch_frontend.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalmatch_frontend.data.api.SearchApi
import com.example.signalmatch_frontend.data.model.request.SearchRequest
import com.example.signalmatch_frontend.data.model.response.InvestorSearchDto
import com.example.signalmatch_frontend.data.model.response.StartupSearchDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchApi: SearchApi
) : ViewModel() {

    var query by mutableStateOf("")
        private set

    var selectedAreas by mutableStateOf<List<String>>(emptyList())
        private set

    var startups by mutableStateOf<List<StartupSearchDto>>(emptyList())
        private set

    var investors by mutableStateOf<List<InvestorSearchDto>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var isSearched by mutableStateOf(false)
        private set

    fun onQueryChange(newQuery: String) {
        query = newQuery
    }

    fun toggleArea(area: String) {
        selectedAreas =
            if (selectedAreas.contains(area)) selectedAreas - area
            else selectedAreas + area
    }

    fun onSearch() {
        viewModelScope.launch {
            try {
                isLoading = true
                isSearched = true

                val request = SearchRequest(
                    keyword = query,
                    areas = selectedAreas.toList(),
                    page = 0,
                    size = 10,
                    sort = emptyList()
                )

                val res = searchApi.search(request)

                startups = res.data.startups
                investors = res.data.investors

            } catch (e: Exception) {
                startups = emptyList()
                investors = emptyList()
            } finally {
                isLoading = false
            }
        }
    }
}
