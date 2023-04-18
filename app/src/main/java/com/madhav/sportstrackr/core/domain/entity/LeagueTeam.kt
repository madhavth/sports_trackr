package com.madhav.sportstrackr.core.domain.entity

import com.madhav.sportstrackr.core.data.models.SocialMediaInfo

data class LeagueTeam(
    val idTeam: String,
    val idSoccerXML: String?,
    val idAPIfootball: String?,
    val intLoved: String?,
    val strTeam: String,
    val strAlternate: String?,
    val intFormedYear: String?,
    val strSport: String,
    val strLeague: String,
    val idLeague: String,
    val strManager: String?,
    val strStadium: String?,
    val strKeywords: String?,
    val strRSS: String?,
    val strStadiumThumb: String?,
    val strStadiumDescription: String?,
    val strStadiumLocation: String?,
    val intStadiumCapacity: String?,
    val socialMediaInfo: SocialMediaInfo,
    val strCountry: String,
    val strDescriptionEN: String?,
    val teamBadge: String = ""
)