package com.madhav.sportstrackr.features.search_add.presentation.view_models

import androidx.lifecycle.ViewModel
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.features.search_add.domain.entities.Country
import com.madhav.sportstrackr.features.search_add.domain.entities.Sport
import com.madhav.sportstrackr.features.search_add.domain.use_cases.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CountrySearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
) : ViewModel() {
    private var _searchQuery: MutableStateFlow<String> = MutableStateFlow("")

    val searchQuery get() = _searchQuery.value

    private val _countriesFetchedResult = MutableStateFlow<MyResponse<List<Country>>>(
        MyResponse.Loading
    )

    private val _sportsSearchResult: MutableStateFlow<MyResponse<List<Country>>> = MutableStateFlow(
        MyResponse.Loading
    )

    val countriesSearchResult: StateFlow<MyResponse<List<Country>>> = _sportsSearchResult


    suspend fun getCountries() {
        _countriesFetchedResult.value = MyResponse.Loading
        val result = searchUseCases.searchCountriesUseCase.execute()
        _countriesFetchedResult.value = MyResponse.Success(result)
        _sportsSearchResult.value = MyResponse.Success(result)
    }

    fun performSearch(query: String) {
        _searchQuery.value = query

        if (_countriesFetchedResult.value is MyResponse.Success) {
            val sports = (_countriesFetchedResult.value as MyResponse.Success).data
            val filteredSports = sports.filter { it.name.contains(query, true) }
            _sportsSearchResult.value = MyResponse.Success(filteredSports)
        }

    }
}