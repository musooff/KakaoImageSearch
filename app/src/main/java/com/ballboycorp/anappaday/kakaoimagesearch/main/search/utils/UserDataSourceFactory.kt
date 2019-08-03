package com.ballboycorp.anappaday.kakaoimagesearch.main.search.utils

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.ballboycorp.anappaday.kakaoimagesearch.model.Image
import com.ballboycorp.anappaday.kakaoimagesearch.network.KakaoService
import com.ballboycorp.anappaday.kakaoimagesearch.network.model.NetworkState
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

    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Image>
    ) {
        compositeDisposable.add(
            KakaoService()
                .images(query, 1, params.requestedLoadSize)
                .map { it.documents }
                .subscribe({
                    callback.onResult(
                        it,
                        null,
                        if (it.size == params.requestedLoadSize) 2 else null
                    )
                    networkState.value = NetworkState.SUCCESS
                }, {
                    callback.onResult(emptyList(), null, null)
                    networkState.value = NetworkState.ERROR
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
                .map { it.documents }
                .subscribe({ result ->
                    callback.onResult(
                        result,
                        params.key.plus(1).takeIf { result.size >= params.requestedLoadSize })
                    networkState.value = NetworkState.SUCCESS
                }, {
                    networkState.value = NetworkState.ERROR
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
    }
}