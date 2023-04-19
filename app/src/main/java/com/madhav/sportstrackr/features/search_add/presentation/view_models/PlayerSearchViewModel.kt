package com.madhav.sportstrackr.features.search_add.presentation.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.core.domain.entity.PlayerInfo
import com.madhav.sportstrackr.features.search_add.domain.use_cases.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PlayerSearchViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val searchUseCases: SearchUseCases
) : ViewModel() {

    private val _searchQuery = MutableStateFlow<String?>(null)
    val searchQuery = _searchQuery.asStateFlow()


    private var job: Job = Job()
    private var searchScope = CoroutineScope(Dispatchers.IO + job)


    private val _teamPlayers: MutableStateFlow<MyResponse<List<PlayerInfo>>> =
        MutableStateFlow(MyResponse.Loading)
    val teamPlayers = _teamPlayers.asStateFlow()

    private var _teamId: String? = null
    val teamId: String?
        get() = _teamId

    private var _teamName: String? = null
    val teamName: String?
        get() = _teamName

    fun setTeamId(id: String?) {
        _teamId = id
    }

    fun setTeamName(name: String?) {
        _teamName = name
    }


    private fun cancelAndInitSearchScope() {
        job.cancel()
        job = Job()
        searchScope = CoroutineScope(Dispatchers.IO + job)
    }

    // add a 1sec bounce to the search
    fun performSearch(query: String) {
        viewModelScope.launch {
            if (_searchQuery.value == query) return@launch

            cancelAndInitSearchScope()

            _searchQuery.value = query
            _teamPlayers.value = MyResponse.Loading

            withContext(searchScope.coroutineContext) {
                delay(1000)
                searchPlayers(query)
            }
        }
    }

    private suspend fun searchPlayers(query: String) {
        if (teamName == null) return

        val result = searchUseCases.searchPlayerUseCase.execute(teamName!!, query)
        _teamPlayers.value = MyResponse.Success(result)
    }

}