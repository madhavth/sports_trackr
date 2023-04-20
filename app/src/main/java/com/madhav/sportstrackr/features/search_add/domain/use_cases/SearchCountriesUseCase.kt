package com.madhav.sportstrackr.features.search_add.domain.use_cases

import com.madhav.sportstrackr.features.search_add.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchCountriesUseCase @Inject constructor(
    private val searchRepository: SearchRepository
){
    suspend fun execute() = searchRepository.getAllCountries().countries.map {
        it.toDomain()
    }
}