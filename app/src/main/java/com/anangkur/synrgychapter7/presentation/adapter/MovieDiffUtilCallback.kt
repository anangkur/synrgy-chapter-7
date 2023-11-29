package com.anangkur.synrgychapter7.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.anangkur.domain.Movie

class MovieDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(
        oldItem: Movie,
        newItem: Movie,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Movie,
        newItem: Movie,
    ): Boolean {
        return oldItem.id == newItem.id
    }
}
