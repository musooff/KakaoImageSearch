package com.ballboycorp.anappaday.kakaoimagesearch.main.search.utils

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ballboycorp.anappaday.kakaoimagesearch.common.PagedNetworkResult
import com.ballboycorp.anappaday.kakaoimagesearch.model.Image
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by musooff on 2019-08-02.
 */

class SearchRepository(private val compositeDisposable: CompositeDisposable) {
    companion object {
        const val PAGE_SIZE = 25
    }

    private val config: PagedList.Config = PagedList.Config.Builder().apply {
        setInitialLoadSizeHint(PAGE_SIZE)
        setPageSize(PAGE_SIZE)
        setPrefetchDistance(5)
        setEnablePlaceholders(true)
    }.build()

    fun searchUsers(query: String): PagedNetworkResult<Image> {
        val sourceFactory =
            UserDataSourceFactory(compositeDisposable, query)
        val livePagedList = LivePagedListBuilder(sourceFactory, config).build()
        return PagedNetworkResult(
            data = livePagedList,
            state = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkState })
    }
}