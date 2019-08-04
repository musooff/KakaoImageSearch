package com.ballboycorp.anappaday.kakaoimagesearch.utils.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * Created by musooff on 2019-08-02.
 */


inline fun <reified T: ViewModel> FragmentActivity.getViewModel(factory: ViewModelProvider.Factory? = null): T {
    return ViewModelProviders
        .of(this, factory)
        .get(T::class.java)
}

inline fun <reified T: ViewModel> Fragment.getViewModel(factory: ViewModelProvider.Factory? = null): T {
    return ViewModelProviders
        .of(this, factory)
        .get(T::class.java)
}

inline fun <reified T: ViewModel> Fragment.getAppViewModel(factory: ViewModelProvider.Factory? = null): T {
    return ViewModelProviders
        .of(activity!!, factory)
        .get(T::class.java)
}