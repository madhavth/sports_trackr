package com.madhav.sportstrackr.core.ui.views

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.core.theme.DarkGray

@Composable
fun CircleAvatar(image: Painter, modifier: Modifier = Modifier, size: Int= 64, description:String?= null) {
    Image(
        painter = image,
        contentDescription = description,
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(DarkGray)
    )
}


@Preview
@Composable
fun CircleAvatarPreview() {
    CircleAvatar(
        image = painterResource(R.drawable.ic_launcher_foreground),
        modifier = Modifier.padding(16.dp)
    )
}