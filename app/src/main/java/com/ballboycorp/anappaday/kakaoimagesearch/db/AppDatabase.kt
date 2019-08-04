package com.ballboycorp.anappaday.kakaoimagesearch.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ballboycorp.anappaday.kakaoimagesearch.db.converters.MainConverters
import com.ballboycorp.anappaday.kakaoimagesearch.db.dao.SearchDao
import com.ballboycorp.anappaday.kakaoimagesearch.model.Search

/**
 * Created by musooff on 2019-08-04.
 */

@Database(
    entities = [
        Search::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(MainConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchDao(): SearchDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context, AppDatabase::class.java, "application.db")
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }

    }
}