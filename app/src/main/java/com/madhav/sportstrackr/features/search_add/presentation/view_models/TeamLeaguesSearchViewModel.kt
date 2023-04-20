package com.madhav.sportstrackr.features.search_add.presentation.view_models

import androidx.lifecycle.ViewModel
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.features.search_add.domain.use_cases.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TeamLeaguesSearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
) : ViewModel() {
    private var _searchQuery: MutableStateFlow<String> = MutableStateFlow("")

    val searchQuery get() = _searchQuery.value


    private val _leagueTeamsFetchedResults = MutableStateFlow<MyResponse<List<LeagueTeam>>>(
        MyResponse.Loading
    )

    private val _leagueTeamSearchResult: MutableStateFlow<MyResponse<List<LeagueTeam>>> = MutableStateFlow(
        MyResponse.Loading
    )

    val leagueSearchResult: StateFlow<MyResponse<List<LeagueTeam>>> = _leagueTeamSearchResult

    suspend fun getLeagueTeams(leagueName: String?) {
        _leagueTeamsFetchedResults.value = MyResponse.Loading

        val result = searchUseCases.leagueTeamsSearchUseCase.execute(leagueName ?: "")
        _leagueTeamsFetchedResults.value = MyResponse.Success(result)
        _leagueTeamSearchResult.value = MyResponse.Success(result)
    }


    fun performSearch(query: String) {
        _searchQuery.value = query

        if (_leagueTeamsFetchedResults.value is MyResponse.Success) {
            val teams = (_leagueTeamsFetchedResults.value as MyResponse.Success).data
            val filteredTeams = teams.filter { it.strTeam.contains(query, true) }
            _leagueTeamSearchResult.value = MyResponse.Success(filteredTeams)
        }

    }
}