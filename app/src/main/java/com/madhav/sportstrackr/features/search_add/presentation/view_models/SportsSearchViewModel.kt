package com.madhav.sportstrackr.features.search_add.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.features.search_add.domain.entities.Sport
import com.madhav.sportstrackr.features.search_add.domain.use_cases.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SportsSearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
) : ViewModel() {
    private var _searchQuery: MutableStateFlow<String> = MutableStateFlow("")

    val searchQuery get() = _searchQuery.value


    private val _sportsFetchedResults = MutableStateFlow<MyResponse<List<Sport>>>(
        MyResponse.Loading
    )

    private val _sportsSearchResult: MutableStateFlow<MyResponse<List<Sport>>> = MutableStateFlow(
        MyResponse.Loading
    )

    val sportSearchResult: StateFlow<MyResponse<List<Sport>>> = _sportsSearchResult


    suspend fun getSports() {
        _sportsFetchedResults.value = MyResponse.Loading

        val result = searchUseCases.sportsUseCase.execute()
        _sportsFetchedResults.value = MyResponse.Success(result)
        _sportsSearchResult.value = MyResponse.Success(result)
    }


    fun performSearch(query: String) {
        _searchQuery.value = query

        if (_sportsFetchedResults.value is MyResponse.Success) {
            val sports = (_sportsFetchedResults.value as MyResponse.Success).data
            val filteredSports = sports.filter { it.name.contains(query, true) }
            _sportsSearchResult.value = MyResponse.Success(filteredSports)
        }

    }
}