package com.ballboycorp.anappaday.kakaoimagesearch.utils

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.ballboycorp.anappaday.kakaoimagesearch.R
import com.ballboycorp.anappaday.kakaoimagesearch.common.HorizontalSpaceDecoration
import com.ballboycorp.anappaday.kakaoimagesearch.common.VerticalSpaceDecoration
import com.ballboycorp.anappaday.kakaoimagesearch.utils.extensions.loadUrl
import com.ballboycorp.anappaday.kakaoimagesearch.utils.extensions.removeView
import com.ballboycorp.anappaday.kakaoimagesearch.utils.extensions.showView

/**
 * Created by musooff on 2019-08-02.
 */

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:emptyItemDecorator")
    fun setEmptyItemDecorator(recyclerView: RecyclerView, isHorizontal: Boolean = false) {
        if (isHorizontal) {
            recyclerView.addItemDecoration(
                HorizontalSpaceDecoration(
                    recyclerView.context,
                    R.dimen.default_item_margin
                )
            )
        } else {
            recyclerView.addItemDecoration(
                VerticalSpaceDecoration(
                    recyclerView.context,
                    R.dimen.default_item_margin
                )
            )
        }
    }

    @JvmStatic
    @BindingAdapter("app:url")
    fun setImageUrl(imageView: ImageView, url: String?) {
        imageView.loadUrl(url, R.color.colorPlaceHolder)
    }

    @JvmStatic
    @BindingAdapter("app:url", "app:heightRatio", "app:widthRatio")
    fun setImageUrlWithRadio(
        imageView: ImageView,
        url: String?,
        heightRatio: Int,
        widthRadio: Int
    ) {
        val params = imageView.layoutParams as ConstraintLayout.LayoutParams
        params.dimensionRatio = "W, $heightRatio:$widthRadio"
        imageView.layoutParams = params
        imageView.loadUrl(url, R.color.colorPlaceHolder)
    }

    @JvmStatic
    @BindingAdapter("app:shouldAnimate")
    fun shouldAnimate(lottieAnimationView: LottieAnimationView, shouldAnimate: Boolean) {
        if (shouldAnimate) {
            lottieAnimationView.playAnimation()
            lottieAnimationView.showView()
        } else {
            lottieAnimationView.pauseAnimation()
            lottieAnimationView.removeView()
        }
    }
}