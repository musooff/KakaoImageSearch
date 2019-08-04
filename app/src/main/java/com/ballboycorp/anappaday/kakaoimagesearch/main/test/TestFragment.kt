package com.ballboycorp.anappaday.kakaoimagesearch.main.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ballboycorp.anappaday.kakaoimagesearch.base.BaseFragment
import com.ballboycorp.anappaday.kakaoimagesearch.databinding.FragmentTestBinding
import com.ballboycorp.anappaday.kakaoimagesearch.utils.extensions.hideKeyboard
import com.ballboycorp.anappaday.kakaoimagesearch.utils.extensions.showKeyboard
import kotlinx.android.synthetic.main.fragment_test.*

/**
 * Created by musooff on 2019-08-04.
 */

class TestFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTestBinding.inflate(inflater, container, false).apply {
            clickHandler = ClickHandler()
        }.root
    }

    inner class ClickHandler {
        fun onClickSearchButton() {
            if (motionLayout.progress > 0.5f) {
                searchEditText.hideKeyboard()
                motionLayout.transitionToStart()
            } else {
                searchEditText.showKeyboard()
                motionLayout.transitionToEnd()
            }
        }
    }
}