package com.madhav.sportstrackr.core.ui.views

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.madhav.sportstrackr.core.theme.DarkGray

@Composable
fun CircleAvatar(
    image: String, modifier: Modifier = Modifier, size: Int = 64, description: String? = null,
    onClicked: () -> Unit = {},
    isSelected: Boolean
) {
    AsyncImage(
        model = image,
        contentDescription = description,
        modifier = modifier
            .size(size.dp)
            .background(
                if (isSelected) MaterialTheme.colors.primary
                else Color.Transparent, CircleShape
            )
            .padding(if(isSelected) 6.dp else 0.dp)
            .clip(CircleShape)
            .background(DarkGray)
            .clickable { onClicked() }
    )
}


@Preview
@Composable
fun CircleAvatarPreview() {
    CircleAvatar(
        image = "",
        modifier = Modifier.padding(16.dp),
        isSelected = true
    )
}