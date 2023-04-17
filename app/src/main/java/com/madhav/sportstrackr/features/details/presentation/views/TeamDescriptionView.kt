package com.madhav.sportstrackr.features.details.presentation.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TeamDescription(description: String, modifier: Modifier = Modifier) {
    Text(
        text = description,
        style = MaterialTheme.typography.body1
            .copy(lineHeight = 28.sp),
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
}

@Preview
@Composable
fun TeamDescriptionPreview() {
    TeamDescription(description = "Chelsea is a football team")
}