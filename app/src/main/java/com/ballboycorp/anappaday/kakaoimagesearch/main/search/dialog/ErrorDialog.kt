package com.ballboycorp.anappaday.kakaoimagesearch.main.search.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ballboycorp.anappaday.kakaoimagesearch.base.BaseDialog
import com.ballboycorp.anappaday.kakaoimagesearch.databinding.DialogErrorBinding
import com.ballboycorp.anappaday.kakaoimagesearch.utils.Event
import com.ballboycorp.anappaday.kakaoimagesearch.utils.extensions.getAppViewModel

/**
 * Created by musooff on 2019-08-04.
 */

class ErrorDialog : BaseDialog() {

    private val appViewModel by lazy { getAppViewModel<DialogViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogErrorBinding.inflate(inflater, container, false)
        binding.clickHandler = ClickHandler()
        return binding.root
    }

    inner class ClickHandler {
        fun onClickRetry() {
            findNavController().navigateUp()
            appViewModel.retrySearch.value = Event(true)
        }

        fun onClickOkay() {
            findNavController().navigateUp()
        }
    }
}