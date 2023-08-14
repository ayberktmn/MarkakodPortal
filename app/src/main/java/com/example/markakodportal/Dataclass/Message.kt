package com.example.markakodportal.Dataclass

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class Message(
    val content: String,
    val timestamp: Long,
    val comments: MutableList<String>

) {
    fun getFormattedTime(): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

}