package com.madhav.sportstrackr.features.search_add.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.features.search_add.domain.entities.Sport
import com.madhav.sportstrackr.features.search_add.domain.use_cases.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SportsSearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
) : ViewModel() {
    private var _searchQuery: String = ""

    val searchQuery get() = _searchQuery

    private val _sportsSearchResults = MutableStateFlow<MyResponse<List<Sport>>>(
        MyResponse.Loading
    )

    val sportSearchResult: StateFlow<MyResponse<List<Sport>>> = _sportsSearchResults

    suspend fun getSports() {
        _sportsSearchResults.value = MyResponse.Loading

        val result = searchUseCases.sportsUseCase.execute()
        _sportsSearchResults.value = MyResponse.Success(result)
    }

     fun performSearch(query: String) {
        _searchQuery = query
        if (query.length < 3) return

        if(_sportsSearchResults.value is MyResponse.Success) {
            val result = (_sportsSearchResults.value as MyResponse.Success).data
            val filteredResult = result.filter { it.name.contains(query, true) }
            _sportsSearchResults.value = MyResponse.Success(filteredResult)
        }

    }
}