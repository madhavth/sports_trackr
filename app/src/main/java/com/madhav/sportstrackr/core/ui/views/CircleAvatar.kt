package com.madhav.sportstrackr.core.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

sealed class ImageType(val value: String) {
    data class Network(val v: String) : ImageType(v)
    data class NameAvatar(val v: String) : ImageType(v)
    data class Local(val v: String) : ImageType(v)
}

@Composable
fun CircleAvatar(
    image: ImageType, modifier: Modifier = Modifier, size: Int = 64, description: String? = null,
    onClicked: () -> Unit = {},
    isSelected: Boolean,
    fontSize: Int = 24
) {
    val imageModifier = modifier
        .size(size.dp)
        .background(
            if (isSelected) MaterialTheme.colors.primary
            else Color.Transparent, CircleShape
        )
        .padding(if (isSelected) 6.dp else 0.dp)
        .clip(CircleShape)
        .clickable { onClicked() }


    when (image) {
        is ImageType.NameAvatar -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = imageModifier
            ) {
                Text(
                    text = image.value,
                    color = Color.White,
                    fontSize = fontSize.sp
                )
            }
        }
        is ImageType.Network -> {
            NetworkImage(url = image.value, modifier = imageModifier, desc = "profile image")
        }
        is ImageType.Local -> {
            Image(painter = painterResource(id =
            com.madhav.sportstrackr.R.drawable.ic_add
            ),
                contentDescription = image.value)
        }
    }
}


@Preview
@Composable
fun CircleAvatarPreview() {
    CircleAvatar(
        image = ImageType.Network(
            "data:image/png;base64,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"
        ),
        modifier = Modifier.padding(16.dp),
        isSelected = true
    )
}

@Preview(showBackground = true)
@Composable
fun CircleAvatarNamePreview() {
    CircleAvatar(
        image = ImageType.NameAvatar("MT"),
        modifier = Modifier.padding(16.dp),
        isSelected = true
    )


}
@Preview(showBackground = true)
@Composable
fun CircleAvatarLocalPreview() {
    CircleAvatar(
        image = ImageType.Local("MT"),
        modifier = Modifier.padding(16.dp),
        isSelected = true
    )


}