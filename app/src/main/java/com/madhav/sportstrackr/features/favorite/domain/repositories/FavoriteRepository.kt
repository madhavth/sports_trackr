package com.madhav.sportstrackr.features.favorite.domain.repositories

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun addFavoriteTeam(favoriteTeam: FavoriteTeam)
    fun removeFavoriteTeam(favoriteTeam: String)
    fun getFavoriteTeams(signedInAccount: String): Flow<List<FavoriteTeam>>
    fun isFavoriteTeam(id: String): Flow<Boolean>
}