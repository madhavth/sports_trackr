package com.madhav.sportstrackr.features.events.presentation.views

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.features.events.domain.entities.UpComingEvent


@Composable
fun UpcomingEventView(
    upComingEvent: UpComingEvent,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
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

            Row(modifier= Modifier.fillMaxSize()) {
                Text(
                    text = upComingEvent.homeTeam,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.weight(0.45f)
                )
                Text(
                    text = stringResource(R.string.vs),
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .weight(0.1f),
                )
                Text(
                    text = upComingEvent.awayTeam,
                    style = MaterialTheme.typography.h6.copy(
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.weight(0.45f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
                ) {
                Text(
                    text = upComingEvent.date,
                    style = MaterialTheme.typography.body2,
                )
                
                IconButton(onClick = {  },) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = stringResource(R.string.add_calendar),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun UpcomingEventPreview() {
    UpcomingEventView(
        upComingEvent = UpComingEvent(
            homeTeam = "Manchester United",
            awayTeam = "Liverpool",
            date = "2021-08-08",
            id = "123"
        )
    )
}