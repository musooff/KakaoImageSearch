package com.ballboycorp.anappaday.kakaoimagesearch.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by musooff on 2019-08-04.
 */

@Entity
class Search {
    @PrimaryKey
    var id: Int = 0
    var date: Date? = null
    var searchKey: String? = null
}