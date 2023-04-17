package com.madhav.sportstrackr.features.events.presentation.page

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.core.models.TeamScore
import com.madhav.sportstrackr.features.events.domain.entities.PastEvent
import com.madhav.sportstrackr.features.events.domain.entities.UpComingEvent
import com.madhav.sportstrackr.features.events.presentation.views.PastEventView
import com.madhav.sportstrackr.features.events.presentation.views.UpcomingEventView
import java.util.*

@Composable
fun PastFutureEventScreen(
    upcomingEvents: List<UpComingEvent>,
    pastEvents: List<PastEvent>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        content = {

            item {
                Text(
                    stringResource(R.string.upcoming_events),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .fillMaxWidth()
                )
            }



            items(
                upcomingEvents.size, key = { index -> upcomingEvents[index].id },
            ) {
                UpcomingEventView(upcomingEvents[it])
            }

            item {
                Text(
                    stringResource(R.string.past_events),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier
                        .padding(bottom = 24.dp, top= 24.dp)
                        .fillMaxWidth()
                )
            }

            items(pastEvents.size, key = { index -> pastEvents[index].id }) {
                PastEventView(pastEvents[it])
            }
        },
        contentPadding = PaddingValues(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun EventScreenPreview() {
    PastFutureEventScreen(
        listOf(
            UpComingEvent("1", "Chelsea", "Arsenal", "2021-05-05"),
            UpComingEvent("2", "Chelsea", "Arsenal", "2021-05-05"),
            UpComingEvent("3", "Chelsea", "Arsenal", "2021-05-05"),
            UpComingEvent("4", "Chelsea", "Arsenal", "2021-05-05"),
            UpComingEvent("5", "Chelsea", "Arsenal", "2021-05-05"),
        ),
        listOf(
            PastEvent(
                "12345", "EPL", "2021-05-05",
                homeScore = TeamScore("Chelsea", "12"),
                awayScore = TeamScore("Arsenal", "2"),
                time = "12:00"
            ),

            PastEvent(
                "12345", "EPL", "2021-05-05",
                homeScore = TeamScore("Chelsea", "12"),
                awayScore = TeamScore("Arsenal", "2"),
                time = "12:00"
            ),

            PastEvent(
                "12345", "EPL", "2021-05-05",
                homeScore = TeamScore("Chelsea", "12"),
                awayScore = TeamScore("Arsenal", "2"),
                time = "12:00"
            ),
        ),
    )
}
