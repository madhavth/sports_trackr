package com.madhav.sportstrackr.core.data.models

import com.madhav.sportstrackr.core.domain.entity.PlayerInfo

data class PlayerModel(
    val dateBorn: String,
    val dateSigned: String,
    val idAPIfootball: String,
    val idPlayer: String,
    val idPlayerManager: String,
    val idSoccerXML: String,
    val idTeam: String,
    val idTeam2: String,
    val idTeamNational: Any,
    val idWikidata: Any,
    val intLoved: String,
    val strAgent: String?,
    val strBanner: String,
    val strBirthLocation: String,
    val strCollege: Any,
    val strCreativeCommons: String,
    val strCutout: String,
    val strDescriptionEN: String?,
    val strEthnicity: String?,
    val strFacebook: String?,
    val strFanart1: String?,
    val strGender: String?,
    val strHeight: String?,
    val strInstagram: String?,
    val strKit: String?,
    val strLocked: String?,
    val strNationality: String,
    val strNumber: String?,
    val strOutfitter: String?,
    val strPlayer: String?,
    val strPlayerAlternate: String?,
    val strPosition: String?,
    val strRender: String?,
    val strSide: String?,
    val strSigning: String?,
    val strSport: String?,
    val strStatus: String?,
    val strTeam: String,
    val strTeam2: String?,
    val strThumb: String?,
    val strTwitter: String?,
    val strWage: String,
    val strWebsite: String?,
    val strWeight: String,
    val strYoutube: String
) {
    fun toDomain(): PlayerInfo {
        return PlayerInfo(
            playerId = idPlayer,
            playerName = strPlayer ?:"",
            playerPosition = strPosition ?: "",
            nationality = strNationality,
            playerSigning = strSigning?: "",
            playerNumber = strNumber ?: "",
            description = strDescriptionEN,
            dateOfBirth = dateBorn,
            agent = strAgent ?: "",
            socialMediaInfo = SocialMediaInfo(
                facebook = strFacebook ?: "",
                twitter = strTwitter?:"",
                instagram = strInstagram ?: "",
                website = strWebsite ?: ""
            )
        )
    }
}