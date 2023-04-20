package com.madhav.sportstrackr.features.search_add.data.models

import com.madhav.sportstrackr.features.search_add.domain.entities.Country

data class CountryModel(
    val name_en: String
) {
    fun toDomain(): Country {
        return Country(
            name = name_en
        )
    }
}