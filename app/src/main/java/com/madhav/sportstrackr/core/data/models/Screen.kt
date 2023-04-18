package com.madhav.sportstrackr.core.data.models

import androidx.annotation.DrawableRes
import com.madhav.sportstrackr.R

sealed class Screen(val label: String, @DrawableRes val icon: Int) {
    object Events : Screen("Events", R.drawable.ic_events)
    object Details : Screen("Details", R.drawable.ic_details)
    object Search : Screen("Search", R.drawable.ic_search)
    object Profile : Screen("Profile", R.drawable.ic_profile)

    companion object {
        val items = listOf(Events, Details, Search, Profile)
    }
}