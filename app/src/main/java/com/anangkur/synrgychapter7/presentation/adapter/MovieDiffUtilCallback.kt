package com.anangkur.synrgychapter7.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.anangkur.synrgychapter7.presentation.home.MovieUiData

class MovieDiffUtilCallback : DiffUtil.ItemCallback<MovieUiData>() {
    override fun areItemsTheSame(
        oldItem: MovieUiData,
        newItem: MovieUiData,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MovieUiData,
        newItem: MovieUiData,
    ): Boolean {
        return oldItem.title == newItem.title
    }
}
