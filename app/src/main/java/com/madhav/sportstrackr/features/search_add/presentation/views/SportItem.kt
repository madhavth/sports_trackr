package com.madhav.sportstrackr.features.search_add.presentation.views

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.madhav.sportstrackr.features.search_add.domain.entities.Sport

@Composable
fun SportItemView(
    sport: Sport,
    modifier: Modifier = Modifier,
    onClick: (Sport) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onClick(sport) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = sport.imgUrl,
            contentDescription = sport.name,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = sport.name, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
fun SportsItemPreview() {
    SportItemView(
        sport = Sport(
            "Soccer",
            imgUrl = "test.com"
        ),
    )
}