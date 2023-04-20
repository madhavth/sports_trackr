package com.madhav.sportstrackr.core.ui.views

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.core.domain.entity.League

@Composable
fun LeagueItem(league: League, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(
                RoundedCornerShape(16.dp),
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {

            }
        , elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = league.name, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            if (league.country != null) {
                Text(text = league.country, style = MaterialTheme.typography.body2)
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(text = league.sport, style = MaterialTheme.typography.body2)
        }
    }
}

@Preview
@Composable
fun LeagueItemPreview() {
    LeagueItem(league = League(name = "Premier League", country = "England",
    sport = "Soccer"
        )
    )
}