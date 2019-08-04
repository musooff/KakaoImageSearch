package com.ballboycorp.anappaday.kakaoimagesearch.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.Completable

/**
 * Created by musooff on 2019-08-04.
 */

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplace(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplace(obj: List<T>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(obj: List<T>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(obj: T): Completable

    @Delete
    fun delete(obj: T)

    @Update
    fun update(obj: T): Completable
}