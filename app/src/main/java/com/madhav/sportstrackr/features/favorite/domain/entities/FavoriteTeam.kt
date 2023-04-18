package com.madhav.sportstrackr.features.favorite.domain.entities

data class FavoriteTeam(
    val id: String,
    val name: String,
    val bannerImage: String = "",
    val thumbUrl: String,
    val sports: String,
)