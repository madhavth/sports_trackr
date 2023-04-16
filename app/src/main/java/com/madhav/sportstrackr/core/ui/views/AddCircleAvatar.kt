package com.madhav.sportstrackr.core.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.R

@Composable
fun CircleAvatarWithAddButton(
    image: Painter,
    onAddClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        CircleAvatar(image = image)
        IconButton(
            onClick = onAddClicked,
            modifier = Modifier
                .size(24.dp)
                .background(Color.White, CircleShape)
                .padding(2.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add",
                tint = Color.Blue
            )
        }
    }
}


@Preview
@Composable
fun CircleAvatarAddButtonPreview() {
    CircleAvatarWithAddButton(
        image = painterResource(id=R.drawable.ic_launcher_foreground),
        onAddClicked = { /*TODO*/ }
    )
}
