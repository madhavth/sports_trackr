package com.madhav.sportstrackr.core.ui.views

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SearchIcon(modifier: Modifier = Modifier, onClick: ()-> Unit = {}, color: Color = Color.Black) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            tint = color
        )
    }
}