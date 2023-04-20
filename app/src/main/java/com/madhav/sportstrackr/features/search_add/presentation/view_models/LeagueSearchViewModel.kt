package com.madhav.sportstrackr.features.search_add.presentation.view_models

import androidx.lifecycle.ViewModel
import com.madhav.sportstrackr.core.domain.entity.League
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.features.search_add.domain.use_cases.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class LeagueSearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
) : ViewModel() {
    private var _searchQuery: MutableStateFlow<String> = MutableStateFlow("")

    val searchQuery get() = _searchQuery.value

    private val _leaguesFetchedResults = MutableStateFlow<MyResponse<List<League>>>(
        MyResponse.Loading
    )

    private val _leaguesSearchResult: MutableStateFlow<MyResponse<List<League>>> = MutableStateFlow(
        MyResponse.Loading
    )

    val leaguesSearchResult: StateFlow<MyResponse<List<League>>> = _leaguesSearchResult


    suspend fun getLeagues(sport: String? ="", country: String? = "") {
        _leaguesFetchedResults.value = MyResponse.Loading

        val result = searchUseCases.leagueSearchUseCase.execute(sport ?: "", country ?: "")
        _leaguesFetchedResults.value = MyResponse.Success(result)
        _leaguesSearchResult.value = MyResponse.Success(result)
    }


    fun performSearch(query: String) {
        _searchQuery.value = query

        if (_leaguesFetchedResults.value is MyResponse.Success) {
            val sports = (_leaguesFetchedResults.value as MyResponse.Success).data
            val filteredLeagues = sports.filter { it.name.contains(query, true) }
            _leaguesSearchResult.value = MyResponse.Success(filteredLeagues)
        }

    }
}