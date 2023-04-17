package com.madhav.sportstrackr.features.events.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.core.models.TeamScore
import com.madhav.sportstrackr.core.theme.EventCardColor
import com.madhav.sportstrackr.features.events.domain.entities.PastEvent

@Composable
fun PastEventView(event: PastEvent, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column() {
                    Text(
                        text = event.homeScore.teamName,
                        style = MaterialTheme.typography.h6,
                    )
                    Text(
                        text = event.homeScore.score,
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Column() {
                    Text(
                        text = event.awayScore.teamName,
                        style = MaterialTheme.typography.h6,
                    )
                    Text(
                        text = event.awayScore.score.toString(),
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                    )
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = event.date,
                style = MaterialTheme.typography.caption,
            )
        }
    }

}


@Preview
@Composable
fun PastEventPreview() {
    PastEventView(PastEvent(
        id = "1",
        name = "Test Event",
        date = "2021-01-01",
        time = "12:00",
        homeScore = TeamScore("Home", "1"),
        awayScore = TeamScore("Away", "0")
    ))
}