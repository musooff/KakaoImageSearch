package com.ballboycorp.anappaday.kakaoimagesearch.main.search.dialog

import androidx.lifecycle.MutableLiveData
import com.ballboycorp.anappaday.kakaoimagesearch.base.BaseViewModel
import com.ballboycorp.anappaday.kakaoimagesearch.utils.Event

/**
 * Created by musooff on 2019-08-04.
 */

class DialogViewModel: BaseViewModel() {

    val retrySearch: MutableLiveData<Event<Boolean>> = MutableLiveData()
}