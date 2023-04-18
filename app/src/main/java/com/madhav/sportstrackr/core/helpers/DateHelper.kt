package com.madhav.sportstrackr.core.helpers

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import androidx.core.content.ContextCompat.startActivity
import com.madhav.sportstrackr.features.events.domain.entities.UpComingEvent
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun convertStringToDate(dateString: String): Date? {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        return formatter.parse(dateString)
    }

    fun openCalendar(
        event: UpComingEvent,
        context: Context
    ) {
        // Create an intent to open the calendar app
        val intent = Intent(Intent.ACTION_INSERT)
        intent.data = CalendarContract.Events.CONTENT_URI

        val title = "${event.homeTeam} vs ${event.awayTeam}"
        val description = "Game"

        val date  = convertStringToDate(event.date)

        val endTime = date?.time?.plus(48 * 10e9)

        // Set the title, description, and date of the event
        intent.putExtra(CalendarContract.Events.TITLE, title)
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description)
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, date?.time)
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)

        // Start the activity
        startActivity(context, intent, null)
    }

}