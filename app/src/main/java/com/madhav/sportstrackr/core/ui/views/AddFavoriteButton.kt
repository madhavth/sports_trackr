package com.madhav.sportstrackr.core.ui.views

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.madhav.sportstrackr.R

@Composable
fun FavoriteButton(isFavorite: Boolean, onToggleFavorite: (Boolean) -> Unit) {
    IconButton(onClick =  {
        onToggleFavorite(!isFavorite)
    }) {
        Icon(
            painter = if (isFavorite) painterResource(R.drawable.ic_favorite) else painterResource(R.drawable.ic_unfavorite),
            contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
            tint = if (isFavorite) Color.Red else Color.Gray
        )
    }
}

@Preview
@Composable
fun AddFavoritePreview() {
    FavoriteButton(isFavorite = false) {}
}

@Preview
@Composable
fun AddFavoriteIsFavoritePreview() {
    FavoriteButton(isFavorite = true) {}
}


