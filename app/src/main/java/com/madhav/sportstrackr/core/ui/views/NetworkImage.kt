package com.madhav.sportstrackr.core.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.madhav.sportstrackr.R

@Composable
fun NetworkImage(url: String, modifier: Modifier = Modifier, desc: String? = null) {
    AsyncImage(model = ImageRequest
        .Builder(LocalContext.current)
        .data(url)
        .crossfade(true)
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_foreground)
        .build(),

        contentDescription = desc?: url, modifier = modifier)
}