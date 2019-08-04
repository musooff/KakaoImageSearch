package com.ballboycorp.anappaday.kakaoimagesearch.db.converters

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by musooff on 2019-08-04.
 */

class MainConverters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}