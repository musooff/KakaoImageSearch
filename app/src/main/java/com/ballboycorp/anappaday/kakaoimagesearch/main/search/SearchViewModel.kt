package com.ballboycorp.anappaday.kakaoimagesearch.main.search

import android.os.Handler
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.ballboycorp.anappaday.kakaoimagesearch.BR
import com.ballboycorp.anappaday.kakaoimagesearch.base.BaseObservableViewModel
import com.ballboycorp.anappaday.kakaoimagesearch.common.PagedNetworkResult
import com.ballboycorp.anappaday.kakaoimagesearch.main.search.utils.SearchRepository
import com.ballboycorp.anappaday.kakaoimagesearch.model.Image
import com.ballboycorp.anappaday.kakaoimagesearch.network.model.SearchState

/**
 * Created by musooff on 2019-08-03.
 */

class SearchViewModel : BaseObservableViewModel() {
    private val repository = SearchRepository(compositeDisposable)
    private val result = MutableLiveData<PagedNetworkResult<Image>>()
    val pagedList: LiveData<PagedList<Image>> = Transformations.switchMap(result) { it.data }
    val searchState: LiveData<SearchState> = Transformations.switchMap(result) { it.state }

    private val mHandler = Handler()

    var query: String? = ""
        @Bindable get() = field
        set(value) {
            field = value
            searchDelayed()
            notifyPropertyChanged(BR.query)
        }

    var notFound: String? = null
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.notFound)
        }

    var isSearching = false
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.searching)
        }

    fun updateFound(isNotFound: Boolean = true) {
        notFound = "\"$query\"-에 대한 검색 결과가 없습니다.".takeIf { isNotFound }
    }

    fun search() {
        if (query.isNullOrBlank()) return
        updateFound(false)
        query
            ?.takeIf { it.isNotEmpty() }
            ?.let { result.postValue(repository.searchUsers(it)) }
    }

    private fun searchDelayed() {
        if (query.isNullOrBlank()) return
        mHandler.removeCallbacksAndMessages(null)
        mHandler.postDelayed({ search() }, 1000)
    }

    fun clear() {
        query = ""
    }
}