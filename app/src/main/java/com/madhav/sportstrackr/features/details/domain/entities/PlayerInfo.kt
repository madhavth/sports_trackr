package com.madhav.sportstrackr.features.details.domain.entities

import com.madhav.sportstrackr.core.models.SocialMediaInfo

data class PlayerInfo(
    val playerName: String,
    val playerPosition: String,
    val playerId: String,
    val nationality: String,
    val dateOfBirth: String,
    val playerNumber: String,
    val playerSigning: String,
    val description: String,
    val socialMediaInfo: SocialMediaInfo,
    val agent: String
)
