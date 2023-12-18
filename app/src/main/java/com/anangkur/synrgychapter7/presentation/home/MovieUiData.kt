package com.anangkur.synrgychapter7.presentation.home

import com.anangkur.domain.Movie

data class MovieUiData(
    val image: String,
    val title: String,
    val description: String,
)

fun Movie.toMovieUiData(): MovieUiData {
    return MovieUiData(
        image = this.posterPath,
        title = this.originalTitle,
        description = "-",
    )
}
