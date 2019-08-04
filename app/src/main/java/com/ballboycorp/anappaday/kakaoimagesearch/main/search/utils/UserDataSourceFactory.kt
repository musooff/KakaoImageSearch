package com.ballboycorp.anappaday.kakaoimagesearch.main.search.utils

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.ballboycorp.anappaday.kakaoimagesearch.model.Image
import com.ballboycorp.anappaday.kakaoimagesearch.network.KakaoService
import com.ballboycorp.anappaday.kakaoimagesearch.network.model.SearchState
import com.ballboycorp.anappaday.kakaoimagesearch.utils.extensions.observeOnMainThread
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by musooff on 2019-08-02.
 */

class UserDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val query: String
) :
    DataSource.Factory<Int, Image>() {
    val sourceLiveData = MutableLiveData<UserDataSource>()
    override fun create(): DataSource<Int, Image> {
        val userDataSource =
            UserDataSource(compositeDisposable, query)
        sourceLiveData.postValue(userDataSource)
        return userDataSource
    }
}

class UserDataSource(
    private val compositeDisposable: CompositeDisposable,
    private val query: String
) :
    PageKeyedDataSource<Int, Image>() {

    val searchState = MutableLiveData<SearchState>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Image>
    ) {
        compositeDisposable.add(
            KakaoService()
                .images(query, 1, params.requestedLoadSize)
                .observeOnMainThread()
                .doOnSubscribe { searchState.postValue(SearchState.LOADING) }
                .subscribe({
                    callback.onResult(
                        it.documents,
                        null,
                        if (it.documents.size == params.requestedLoadSize) 2 else null
                    )
                    searchState.value =
                        if (it.meta?.isEng == true) SearchState.NOT_FOUND else SearchState.SUCCESS
                }, {
                    callback.onResult(emptyList(), null, null)
                    searchState.value = SearchState.ERROR
                })

        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        compositeDisposable.add(
            KakaoService().images(
                query,
                params.key,
                params.requestedLoadSize
            )
                .observeOnMainThread()
                .doOnSubscribe { searchState.postValue(SearchState.LOADING_MORE) }
                .subscribe({ result ->
                    callback.onResult(
                        result.documents,
                        params.key.plus(1).takeIf { result.documents.size >= params.requestedLoadSize })
                    searchState.value =
                        if (result.meta?.isEng == true) SearchState.END_OF_RESULT else SearchState.SUCCESS
                }, {
                    searchState.value = SearchState.ERROR
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {}
}