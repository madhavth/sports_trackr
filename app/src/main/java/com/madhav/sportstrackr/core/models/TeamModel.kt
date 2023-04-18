package com.madhav.sportstrackr.core.models

import com.madhav.sportstrackr.core.domain.entity.LeagueTeam

data class TeamModel(
    val idAPIfootball: String,
    val idLeague: String,
    val idLeague2: String,
    val idLeague3: String,
    val idSoccerXML: String,
    val idTeam: String,
    val intFormedYear: String,
    val intLoved: String?,
    val intStadiumCapacity: String,
    val strAlternate: String,
    val strCountry: String,
    val strDivision: Any,
    val strFacebook: String,
    val strGender: String,
    val strInstagram: String,
    val strKeywords: String,
    val strKitColour1: String,
    val strKitColour2: String,
    val strKitColour3: String,
    val strLeague: String,
    val strLeague2: String?,
    val strLeague3: String?,
    val strLeague4: String?,
    val strLocked: String,
    val strManager: String,
    val strRSS: String,
    val strSport: String,
    val strStadium: String,
    val strStadiumDescription: String,
    val strDescriptionEN: String?,
    val strStadiumLocation: String?,
    val strStadiumThumb: String?,
    val strTeam: String,
    val strTeamBadge: String,
    val strTeamBanner: String,
    val strTeamFanart1: String?,
    val strTeamFanart2: String?,
    val strTeamFanart3: String?,
    val strTeamFanart4: String?,
    val strTeamJersey: String?,
    val strTeamLogo: String?,
    val strTeamShort: String?,
    val strTwitter: String?,
    val strWebsite: String?,
    val strYoutube: String?
)


fun TeamModel.toLeagueTeamEntity() : LeagueTeam {
    val it = this
    return LeagueTeam(
        idTeam = it.idTeam,
        idSoccerXML = it.idSoccerXML,
        idAPIfootball = it.idAPIfootball,
        intLoved = it.intLoved,
        strTeam = it.strTeam,
        strAlternate = it.strAlternate,
        intFormedYear = it.intFormedYear,
        strSport = it.strSport,
        strLeague = it.strLeague,
        idLeague = it.idLeague,
        strManager = it.strManager,
        strStadium = it.strStadium,
        strKeywords = it.strKeywords,
        strRSS = it.strRSS,
        strStadiumThumb = it.strStadiumThumb,
        strStadiumDescription = it.strStadiumDescription,
        strStadiumLocation = it.strStadiumLocation,
        intStadiumCapacity = it.intStadiumCapacity,
        socialMediaInfo = SocialMediaInfo(
            facebook = it.strFacebook,
            twitter = it.strTwitter ?: "",
            instagram = it.strInstagram,
            website = it.strWebsite ?: ""
        ),
        strCountry = it.strCountry,
        strDescriptionEN = it.strDescriptionEN
    )
}