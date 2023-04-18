package com.madhav.sportstrackr.features.search_add.domain.entities

sealed class SearchMode {
    object Search : SearchMode()
    object Add : SearchMode()
}