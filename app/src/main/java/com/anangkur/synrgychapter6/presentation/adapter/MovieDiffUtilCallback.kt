package com.anangkur.synrgychapter6.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.anangkur.domain.Movie

class MovieDiffUtilCallback : DiffUtil.ItemCallback<com.anangkur.domain.Movie>() {
    override fun areItemsTheSame(oldItem: com.anangkur.domain.Movie, newItem: com.anangkur.domain.Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: com.anangkur.domain.Movie, newItem: com.anangkur.domain.Movie): Boolean {
        return oldItem.id == newItem.id
    }
}