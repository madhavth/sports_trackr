package com.madhav.sportstrackr.features.favorite.domain.entities

data class FavoriteTeam(
    var id: String,
    var name: String,
    var bannerImage: String = "",
    var thumbUrl: String,
    var sports: String,
) {
    constructor() : this("", "", "", "", "")

    companion object {
        fun from(id: String, name: String, bannerImage: String, thumbUrl: String, sports: String): FavoriteTeam {
            return FavoriteTeam(id, name, bannerImage, thumbUrl, sports)
        }
    }
}