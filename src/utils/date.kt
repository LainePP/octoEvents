package com.events.utils

import org.joda.time.DateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun convertDate(dateString: String): DateTime{
    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS'Z'")
    return DateTime(LocalDateTime.parse(dateString, formatter))
}