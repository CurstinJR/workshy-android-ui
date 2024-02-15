package za.co.workshyelec.core.common

import android.util.Log
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

@Serializable
abstract class BaseModel {
    @Transient
    open val createdAt: String = ""

    @Transient
    open val createdBy: String? = null

    @Transient
    open val lastUpdatedBy: String? = null

    @Transient
    open val updatedAt: String = ""

    fun formatTime(timeString: String?): String {
        // Check if the string is null or blank, and return a empty string
        if (timeString.isNullOrBlank()) {
            return ""
        }

        return try {
            // Parse the string to an Instant
            val instant = Instant.parse(timeString)
            // Convert the Instant to LocalDateTime in the system's default timezone
            val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

            // Format the LocalDateTime
            localDateTime.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                    .withLocale(Locale.getDefault())
            )
        } catch (e: Exception) {
            // If there is an exception, return the original string
            Log.e("BaseModel", "formatTime: ", e)
            timeString
        }
    }

    val formattedCreatedAt: String
        get() = formatTime(createdAt)

    val formattedUpdatedAt: String
        get() = formatTime(updatedAt)
}