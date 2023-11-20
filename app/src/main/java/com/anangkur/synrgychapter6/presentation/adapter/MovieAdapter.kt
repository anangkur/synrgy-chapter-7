package com.anangkur.synrgychapter6.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.anangkur.synrgychapter6.databinding.ItemMovieBinding
import com.anangkur.domain.Movie

class MovieAdapter : ListAdapter<com.anangkur.domain.Movie, MovieViewHolder>(MovieDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}