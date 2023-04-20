package com.madhav.sportstrackr.features.profile.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileOption(text:String, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            onClick()
        }) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(text)
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
    }
}