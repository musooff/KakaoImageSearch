package com.ballboycorp.anappaday.kakaoimagesearch.main.search.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ballboycorp.anappaday.kakaoimagesearch.databinding.ItemSearchImageBinding
import com.ballboycorp.anappaday.kakaoimagesearch.main.search.SearchItemViewModel
import com.ballboycorp.anappaday.kakaoimagesearch.model.Image

/**
 * Created by musooff on 2019-08-02.
 */

class SearchResultAdapter: PagedListAdapter<Image, SearchResultAdapter.SearchResultViewHolder>(
    ItemDiffCallBack
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = ItemSearchImageBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        getItem(position)?.let {
            holder.binding.viewModel = SearchItemViewModel(it)
        }
    }

    inner class SearchResultViewHolder(val binding: ItemSearchImageBinding): RecyclerView.ViewHolder(binding.root)
}

object ItemDiffCallBack : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }
}