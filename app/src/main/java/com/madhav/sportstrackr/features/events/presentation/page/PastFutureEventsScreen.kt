package com.madhav.sportstrackr.features.events.presentation.page

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhav.sportstrackr.core.models.TeamScore
import com.madhav.sportstrackr.features.events.domain.entities.PastEvent
import com.madhav.sportstrackr.features.events.domain.entities.UpCompingEvent
import com.madhav.sportstrackr.features.events.presentation.views.PastEventView
import com.madhav.sportstrackr.features.events.presentation.views.UpcomingEventView

@Composable
fun PastFutureEventScreen(
    upcomingEvents: List<UpCompingEvent>,
    pastEvents: List<PastEvent>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        content = {

            item {
                Text(
                    "Upcoming Events",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            items(
                upcomingEvents.size, key = { index -> upcomingEvents[index].id },
            ) {
                UpcomingEventView(upcomingEvents[it])
            }

            item {
                Text(
                    "Past Events",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
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
            UpCompingEvent("1", "Chelsea", "Arsenal", "2021-05-05"),
            UpCompingEvent("2", "Chelsea", "Arsenal", "2021-05-05"),
            UpCompingEvent("3", "Chelsea", "Arsenal", "2021-05-05"),
            UpCompingEvent("4", "Chelsea", "Arsenal", "2021-05-05"),
            UpCompingEvent("5", "Chelsea", "Arsenal", "2021-05-05"),
        ),
        listOf(
            PastEvent(
                "12345", "EPL", "2021-05-05",
                homeScore = TeamScore("Chelsea", 12),
                awayScore = TeamScore("Arsenal", 2),
                time = "12:00"
            ),

            PastEvent(
                "12345", "EPL", "2021-05-05",
                homeScore = TeamScore("Chelsea", 12),
                awayScore = TeamScore("Arsenal", 2),
                time = "12:00"
            ),

            PastEvent(
                "12345", "EPL", "2021-05-05",
                homeScore = TeamScore("Chelsea", 12),
                awayScore = TeamScore("Arsenal", 2),
                time = "12:00"
            ),

            ),


        )
}
