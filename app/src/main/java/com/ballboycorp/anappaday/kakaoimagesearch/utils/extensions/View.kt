package com.ballboycorp.anappaday.kakaoimagesearch.utils.extensions

import android.view.View

/**
 * Created by musooff on 2019-08-04.
 */

fun View.showView() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.hideView() {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

fun View.removeView() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}