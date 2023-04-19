package com.madhav.sportstrackr.core.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircleAvatarWithRemoveButton(
    image: String,
    modifier: Modifier = Modifier,
    onRemoveClicked: () -> Unit,
    onClicked: () -> Unit = {},
    showRemoveButton: Boolean = true,
    isSelected: Boolean,
) {
    Box(modifier = modifier) {
        CircleAvatar(
            image = image,
            onClicked = onClicked,
            isSelected = isSelected
        )
        if (showRemoveButton)
            IconButton(
                onClick = onRemoveClicked,
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.White, CircleShape)
                    .padding(2.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete",
                    tint = Color.Blue
                )
            }
    }
}


@Preview
@Composable
fun CircleAvatarAddButtonPreview() {
    CircleAvatarWithRemoveButton(
        image = "",
        onRemoveClicked = { /*TODO*/ },
        isSelected = true
    )
}
