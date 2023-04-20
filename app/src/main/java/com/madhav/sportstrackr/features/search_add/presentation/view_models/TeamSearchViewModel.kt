package com.madhav.sportstrackr.features.search_add.presentation.view_models

import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.features.search_add.domain.entities.SearchResult
import com.madhav.sportstrackr.features.search_add.domain.entities.Sport
import com.madhav.sportstrackr.features.search_add.domain.use_cases.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TeamSearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchUseCases: SearchUseCases
) : ViewModel() {
    private var _searchQuery: String = ""

    val searchQuery get() = _searchQuery

    private val _teamSearchResults = MutableStateFlow<MyResponse<List<LeagueTeam>>>(
        MyResponse.Success(
            listOf(),
            isInitial = true
        )
    )
    val teamSearchResult: StateFlow<MyResponse<List<LeagueTeam>>> = _teamSearchResults

    private var job: Job = Job()
    private var searchScope = CoroutineScope(Dispatchers.IO + job)

    init {
        viewModelScope.launch {
//        performSearch("Arsenal")
        }
    }

    private fun cancelAndInitSearchScope() {
        job.cancel()
        job = Job()
        searchScope = CoroutineScope(Dispatchers.IO + job)
    }

    // add a 1sec bounce to the search
    suspend fun performSearch(query: String) {
        cancelAndInitSearchScope()
        _searchQuery = query

        if (query.length < 3) return

        _teamSearchResults.value = MyResponse.Loading

        withContext(searchScope.coroutineContext) {
            delay(1000)
            searchTeams(query)
        }
    }

    private suspend fun searchTeams(query: String) {
        try {
            _teamSearchResults.value =
                MyResponse.Success(searchUseCases.searchTeamUseCase.execute(query))
        } catch (e: Exception) {
            if (e is CancellationException) throw e

            _teamSearchResults.value = MyResponse.Error("Something went wrong")
            e.printStackTrace()
        }
    }
}