package com.ballboycorp.anappaday.kakaoimagesearch.utils.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by musooff on 2019-08-02.
 */

fun ImageView.loadUrl(url: String?, placeholder: Drawable? = null) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun ImageView.loadUrl(url: String?, placeholder: Int? = null) {
    Glide.with(context)
        .load(url)
        .into(this)
}