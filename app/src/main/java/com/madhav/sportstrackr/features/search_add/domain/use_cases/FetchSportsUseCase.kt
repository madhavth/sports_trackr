package com.madhav.sportstrackr.features.search_add.domain.use_cases

import com.madhav.sportstrackr.features.favorite.data.repositories.UserRepository
import com.madhav.sportstrackr.features.search_add.data.data_sources.remote.SearchDataSource
import com.madhav.sportstrackr.features.search_add.data.models.SportsModel
import com.madhav.sportstrackr.features.search_add.domain.entities.Sport
import com.madhav.sportstrackr.features.search_add.domain.repositories.SearchRepository
import javax.inject.Inject

class FetchSportsUseCase @Inject constructor(
    private val searchRepo: SearchRepository
){
    suspend fun execute(): List<Sport> {
        return searchRepo.getSports().sports?.map {
            it.toDomain()
        } ?: listOf()
    }
}
