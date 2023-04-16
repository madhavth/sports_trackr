package com.madhav.sportstrackr.features.splash.domain.entities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

sealed class SwipeScreen(val icon: ImageVector, val title: String, val subtitle: String) {
    object Screen1 : SwipeScreen(
        Icons.Filled.ArrowForward,
        "Track Your Runs",
        "Record your runs and track your progress"
    )

    object Screen2 : SwipeScreen(
        Icons.Filled.ArrowForward,
        "Track Your Workouts",
        "Record your workouts and track your progress"
    )

    object Screen3 : SwipeScreen(
        Icons.Filled.ArrowForward,
        "Track Your Games",
        "Record your games and track your progress"
    )

    object Screen4 : SwipeScreen(
        Icons.Filled.ArrowForward,
        "Get Started",
        "Join our community and start tracking today!"
    )
}