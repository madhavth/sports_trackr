package com.madhav.sportstrackr.core.helpers

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import androidx.core.content.ContextCompat.startActivity
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.features.events.domain.entities.UpComingEvent
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
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

        val title = event.homeTeam + context.getString(R.string.vs_) + event.awayTeam
        val description = context.getString(R.string.game)

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

    fun formatDateTime(dateTimeString: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val dateTime = dateFormat.parse(dateTimeString)
        val dateFormatter = SimpleDateFormat("d'${getDaySuffix(dateTime.date)}' MMMM, yyyy", Locale.getDefault())
        val timeFormatter = SimpleDateFormat("h:mm a", Locale.getDefault())
        return "${dateFormatter.format(dateTime)} @ ${timeFormatter.format(dateTime)}".toLowerCase().capitalize()
    }

    fun getDaySuffix(day: Int): String {
        return when (day) {
            1, 21, 31 -> "st"
            2, 22 -> "nd"
            3, 23 -> "rd"
            else -> "th"
        }
    }

}