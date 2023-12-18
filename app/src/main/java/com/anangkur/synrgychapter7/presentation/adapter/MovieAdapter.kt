package com.anangkur.synrgychapter7.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.anangkur.synrgychapter7.databinding.ItemMovieBinding
import com.anangkur.synrgychapter7.presentation.home.MovieUiData

class MovieAdapter : ListAdapter<MovieUiData, MovieViewHolder>(MovieDiffUtilCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }
}
