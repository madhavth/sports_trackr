package com.madhav.sportstrackr.features.search_add.presentation.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.features.search_add.domain.entities.Country
import com.madhav.sportstrackr.features.search_add.presentation.view_models.CountrySearchViewModel
import com.madhav.sportstrackr.features.search_add.presentation.views.CountryCardView

@Composable
fun CountriesSearchScreen(
    modifier: Modifier = Modifier,
    onCountrySelected: (Country) -> Unit,
    onBackPressed: () -> Unit
) {

    val countriesSearchViewModel: CountrySearchViewModel = hiltViewModel()
    val countriesListState = countriesSearchViewModel.countriesSearchResult.collectAsState()

    GeneralSearchScreen(
        listState = countriesListState.value,
        successView = {
            CountryCardView(country = it, onCountrySelected = onCountrySelected)
        },
        checkEmptyCondition = {
            it.isEmpty() && countriesSearchViewModel.searchQuery.isNotEmpty()
        },
        fetchData = {
            countriesSearchViewModel.getCountries()
        },
        performSearch = {
            countriesSearchViewModel.performSearch(it)
        },
        appBarTitle = "Search by country",
        onBackPressed =  onBackPressed
    )

}