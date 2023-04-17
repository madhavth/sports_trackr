package com.madhav.sportstrackr.core.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val label: String
)