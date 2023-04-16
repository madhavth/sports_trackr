package com.madhav.sportstrackr.features.events.presentation.views

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.features.events.domain.entities.UpCompingEvent

@Composable
fun UpcomingEventView(
    upComingEvent: UpCompingEvent,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { /* Handle click event */ })
            .border(
                width = 2.dp,
                color = Color(0xFFE0E0E0),
                shape = RoundedCornerShape(16.dp)
            ),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = upComingEvent.homeTeam,
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = "vs",
                style = MaterialTheme.typography.body1,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = upComingEvent.awayTeam,
                style = MaterialTheme.typography.h6,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = upComingEvent.date,
                style = MaterialTheme.typography.caption,
            )
        }
    }
}

@Preview
@Composable
fun UpcomingEventPreview() {
    UpcomingEventView(
        upComingEvent = UpCompingEvent(
            homeTeam = "Manchester United",
            awayTeam = "Liverpool",
            date = "2021-08-08"
        )
    )
}