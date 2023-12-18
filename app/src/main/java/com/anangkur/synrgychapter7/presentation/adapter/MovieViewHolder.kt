package com.anangkur.synrgychapter7.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.anangkur.synrgychapter7.databinding.ItemMovieBinding
import com.anangkur.synrgychapter7.presentation.home.MovieUiData

class MovieViewHolder(
    private val binding: ItemMovieBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieUiData) {
        binding.tvTitleMovie.text = movie.title
        binding.tvDescMovie.text = movie.description
        binding.ivMovie.load("https://image.tmdb.org/t/p/w500${movie.image}")
    }
}
