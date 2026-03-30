package com.nexum.linkash.core.database.converter

import androidx.room.TypeConverter
import java.time.Instant

class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Instant? = value?.let { Instant.ofEpochMilli(it) }

    @TypeConverter
    fun toTimestamp(instant: Instant?): Long? = instant?.toEpochMilli()
}
