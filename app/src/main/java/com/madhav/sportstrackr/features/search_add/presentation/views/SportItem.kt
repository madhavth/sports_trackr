package com.madhav.sportstrackr.features.search_add.presentation.views

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.madhav.sportstrackr.core.ui.views.NetworkImage
import com.madhav.sportstrackr.features.search_add.domain.entities.Sport

@Composable
fun SportItemView(
    sport: Sport,
    modifier: Modifier = Modifier,
    onClick: (Sport) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onClick(sport) }
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NetworkImage(
                url = sport.imgUrl, modifier = Modifier.size(48.dp),
                desc = sport.name
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = sport.name, fontWeight = FontWeight.Bold)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Done"
            )
        }
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