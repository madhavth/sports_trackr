package com.madhav.sportstrackr.features.search_add.domain.repositories

import com.madhav.sportstrackr.core.data.models.TeamDetailsModel

interface SearchRepository {
    suspend fun searchTeams(query: String): TeamDetailsModel
}