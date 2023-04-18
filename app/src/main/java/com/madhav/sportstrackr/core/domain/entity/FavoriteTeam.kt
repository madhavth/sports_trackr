package com.madhav.sportstrackr.core.domain.entity

data class FavoriteTeam(
    val id: String,
    val name: String,
    val bannerImage: String = "",
    val thumbUrl: String,
    val sports: String,
)