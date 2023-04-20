package com.madhav.sportstrackr.core.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.madhav.sportstrackr.R

@Composable
fun NetworkImage(url: String, modifier: Modifier = Modifier, desc: String? = null,
crossFade: Boolean = false
                 ) {
    AsyncImage(model = ImageRequest
        .Builder(LocalContext.current)
        .data(url)
        .crossfade(crossFade)
//        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_placeholder)
        .scale(Scale.FILL)
        .build(),

        contentDescription = desc?: url, modifier = modifier)
}