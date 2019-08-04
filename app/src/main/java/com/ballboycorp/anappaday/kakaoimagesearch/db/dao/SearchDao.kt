package com.ballboycorp.anappaday.kakaoimagesearch.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.ballboycorp.anappaday.kakaoimagesearch.model.Search
import io.reactivex.Single

/**
 * Created by musooff on 2019-08-04.
 */

@Dao
interface SearchDao : BaseDao<Search> {

    @Query("SELECT * from Search ORDER BY date DESC")
    fun getAll(): Single<List<Search>>
}