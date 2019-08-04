package com.ballboycorp.anappaday.kakaoimagesearch.common

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ballboycorp.anappaday.kakaoimagesearch.network.model.SearchState

/**
 * Created by musooff on 2019-08-02.
 */

open class PagedNetworkResult<T>(
        var data: LiveData<PagedList<T>>, var state: LiveData<SearchState>
)