package com.madhav.sportstrackr.features.favorite.data.repositories

import com.madhav.sportstrackr.features.favorite.data.data_sources.remote.FavoritesRemoteDataSource
import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam
import com.madhav.sportstrackr.features.favorite.domain.repositories.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val firebaseRemoteDataSource: FavoritesRemoteDataSource
) : FavoriteRepository{
    override fun addFavoriteTeam(favoriteTeam: FavoriteTeam) {
        return firebaseRemoteDataSource.addFavoriteTeam(favoriteTeam)
    }

    override fun removeFavoriteTeam(favoriteTeam: String) {
        return firebaseRemoteDataSource.removeFavoriteTeam(favoriteTeam)
    }

    override fun getFavoriteTeams(): Flow<List<FavoriteTeam>> {
        return firebaseRemoteDataSource.getFavoriteTeams()
    }

    override fun isFavoriteTeam(id: String): Flow<Boolean> {
        return firebaseRemoteDataSource.isFavoriteTeam(id)
    }
}