package com.appsv.todofirebase.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


fun formatTimestampToDateTime(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMM, h:mm a, yyyy", Locale.ENGLISH)
    sdf.timeZone = TimeZone.getTimeZone("Asia/Kolkata") // Set to IST
    return sdf.format(Date(timestamp))
}