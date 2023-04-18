package com.madhav.sportstrackr.core.domain.repository

import com.madhav.sportstrackr.core.domain.entity.FavoriteTeam
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun addFavoriteTeam(favoriteTeam: FavoriteTeam)
    suspend fun removeFavoriteTeam(favoriteTeam: FavoriteTeam)
    suspend fun getFavoriteTeams(): Flow<FavoriteTeam>
    suspend fun isFavoriteTeam(id: String): Flow<Boolean>
}