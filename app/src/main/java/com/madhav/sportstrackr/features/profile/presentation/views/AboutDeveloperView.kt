package com.madhav.sportstrackr.features.profile.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutDeveloperView(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.padding(12.dp)
    ) {
        Text(text = "About Developer")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "This app is developed by Madhav")
    }
}