package com.madhav.sportstrackr.core.domain.entity

import com.madhav.sportstrackr.core.data.models.SocialMediaInfo

data class PlayerInfo(
    val playerName: String,
    val playerPosition: String,
    val playerId: String,
    val nationality: String,
    val dateOfBirth: String,
    val playerNumber: String,
    val playerSigning: String,
    val description: String?,
    val socialMediaInfo: SocialMediaInfo,
    val agent: String
)
