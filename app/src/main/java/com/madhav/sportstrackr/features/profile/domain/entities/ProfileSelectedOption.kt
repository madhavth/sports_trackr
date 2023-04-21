package com.madhav.sportstrackr.features.profile.domain.entities

sealed class ProfileSelectedOption {
    object AboutDeveloper : ProfileSelectedOption()
    object Initial : ProfileSelectedOption()
}