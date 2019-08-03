package com.ballboycorp.anappaday.kakaoimagesearch.base

import androidx.lifecycle.ViewModel
import com.ballboycorp.anappaday.kakaoimagesearch.network.KakaoService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by musooff on 2019-08-02.
 */

open class BaseViewModel : ViewModel() {

    val kakaoService by lazy { KakaoService() }

    protected val compositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
        super.onCleared()
    }
}