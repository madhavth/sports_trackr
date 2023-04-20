package com.madhav.sportstrackr.features.events.presentation.views

import android.app.Activity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.core.helpers.DateHelper
import com.madhav.sportstrackr.core.helpers.PermissionHelper
import com.madhav.sportstrackr.core.ui.views.NetworkImage
import com.madhav.sportstrackr.features.events.domain.entities.UpComingEvent
import com.madhav.sportstrackr.features.events.presentation.view_models.EventViewModel
import kotlinx.coroutines.launch


@Composable
fun UpcomingEventView(
    upComingEvent: UpComingEvent,
    modifier: Modifier = Modifier
) {
    val eventViewModel = hiltViewModel<EventViewModel>()
    val context = LocalContext.current as Activity
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

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

            Row(modifier = Modifier.fillMaxSize()) {
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

            NetworkImage(
                url = upComingEvent.bannerImage,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                desc = upComingEvent.bannerImage,
                crossFade = true
            )

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = DateHelper.formatDateTime(upComingEvent.date),
                    style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.SemiBold,
                    ),
                )

                IconButton(
                    onClick = {
                        // check permission
                        eventViewModel.addEventToCalendar(upComingEvent, context)
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                context.resources.getString(R.string.event_added_calendar),
                                actionLabel = context.resources.getString(R.string.ok),
                            )
                        }
                    },
                ) {
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
            id = "123",
            bannerImage = "https://www.thesportsdb.com/images/media/event/thumb/8x2x2v1600000000.jpg"
        )
    )
}