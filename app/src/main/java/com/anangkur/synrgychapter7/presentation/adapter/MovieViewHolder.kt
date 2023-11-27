package com.anangkur.synrgychapter7.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.anangkur.domain.Movie
import com.anangkur.synrgychapter7.databinding.ItemMovieBinding

class MovieViewHolder(
    private val binding: ItemMovieBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        binding.tvTitleMovie.text = movie.originalTitle
        binding.tvDescMovie.text = movie.overview
        binding.ivMovie.load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
    }
}