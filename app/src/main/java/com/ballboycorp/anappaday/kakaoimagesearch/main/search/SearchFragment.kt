package com.ballboycorp.anappaday.kakaoimagesearch.main.search

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ballboycorp.anappaday.kakaoimagesearch.base.BaseFragment
import com.ballboycorp.anappaday.kakaoimagesearch.databinding.FragmentSearchBinding
import com.ballboycorp.anappaday.kakaoimagesearch.main.search.dialog.DialogViewModel
import com.ballboycorp.anappaday.kakaoimagesearch.main.search.utils.SearchResultAdapter
import com.ballboycorp.anappaday.kakaoimagesearch.network.model.SearchState
import com.ballboycorp.anappaday.kakaoimagesearch.utils.extensions.getAppViewModel
import com.ballboycorp.anappaday.kakaoimagesearch.utils.extensions.getViewModel
import com.ballboycorp.anappaday.kakaoimagesearch.utils.extensions.hideKeyboard
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * Created by musooff on 2019-08-03.
 */

class SearchFragment : BaseFragment() {

    private val viewModel by lazy { getViewModel<SearchViewModel>() }
    private val appViewModel by lazy { getAppViewModel<DialogViewModel>() }
    private val adapter = SearchResultAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }


    private fun initialize() {
        initializeViews()

        initializeViewModel()
    }


    private fun initializeViews() {

        rvSearchResults.adapter = adapter

        searchEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search()
                searchEditText.hideKeyboard()
            }
            return@OnEditorActionListener true
        })


    }


    private fun initializeViewModel() {

        viewModel.pagedList.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.searchState.observe(this, Observer {
            when (it) {
                SearchState.NOT_FOUND -> {
                    viewModel.isSearching = false
                    viewModel.updateFound()
                }
                SearchState.LOADING -> {
                }
                SearchState.LOADING_MORE -> {
                }
                SearchState.SUCCESS -> {
                    viewModel.isSearching = false
                }
                SearchState.END_OF_RESULT -> {
                    viewModel.isSearching = false
                }
                SearchState.ERROR -> {
                    viewModel.isSearching = false
                    viewModel.pagedList.value?.let {
                        if (it.isNotEmpty()) {
                            // can't load more
                        } else {
                            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToErrorDialog())
                        }
                    }
                }
                else -> {
                }
            }
        })

        appViewModel.retrySearch.observe(this, Observer {
            viewModel.search()
        })
    }
}