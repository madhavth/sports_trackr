package com.madhav.sportstrackr.core.helpers

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.provider.CalendarContract
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.madhav.sportstrackr.features.events.domain.entities.UpComingEvent
import java.text.DateFormat
import java.util.*

object PermissionHelper {

    fun checkGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun checkCalendarPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_CALENDAR
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_CALENDAR
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestCalendarPermission(activity: Activity) {


        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR
            ), 1
        )
    }


    fun addDateToCalendar(
        context: Context,
        event: UpComingEvent
    ) {
        val calendar = Calendar.getInstance()

        val date = DateHelper.convertStringToDate(event.date)

        if (date != null) {
            calendar.time = date
        }

        val title = "${event.homeTeam} vs ${event.awayTeam}"
        val description = "Game"

        val contentResolver = context.contentResolver

        val event = ContentValues().apply {
            put(CalendarContract.Events.TITLE, title)
            put(CalendarContract.Events.DESCRIPTION, description)
            put(CalendarContract.Events.DTSTART, calendar.timeInMillis)
            put(CalendarContract.Events.DTEND, calendar.timeInMillis + 4800000)
            put(CalendarContract.Events.ALL_DAY, 0) // Not an all-day event
            put(CalendarContract.Events.CALENDAR_ID, event.id)
            put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
        }

        // Insert the event into the calendar
        contentResolver.insert(CalendarContract.Events.CONTENT_URI, event)
    }

}