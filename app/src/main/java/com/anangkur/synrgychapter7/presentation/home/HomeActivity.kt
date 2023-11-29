package com.anangkur.synrgychapter7.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.anangkur.domain.Movie
import com.anangkur.synrgychapter7.Application
import com.anangkur.synrgychapter7.databinding.ActivityHomeBinding
import com.anangkur.synrgychapter7.presentation.adapter.MovieAdapter
import com.anangkur.synrgychapter7.presentation.profile.ProfileActivity
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {
    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }

    private var binding: ActivityHomeBinding? = null
    private var movieAdapter: MovieAdapter? = null

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as Application).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        observeLiveData()
        setupMovieAdapter()

        binding?.buttonProfile?.setOnClickListener {
            ProfileActivity.startActivity(this)
        }

        viewModel.fetchMovies()

        binding?.root?.setOnRefreshListener { viewModel.fetchMovies() }
    }

    private fun setupMovieAdapter() {
        movieAdapter = MovieAdapter()
        binding?.recyclerMovie?.adapter = movieAdapter
        binding?.recyclerMovie?.layoutManager = LinearLayoutManager(this)
    }

    private fun observeLiveData() {
        viewModel.loading.observe(this, ::handleLoading)
        viewModel.error.observe(this, ::handleError)
        viewModel.movies.observe(this, ::handleMovies)
    }

    private fun handleLoading(isLoading: Boolean) {
        binding?.progress?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun handleError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun handleMovies(movies: List<Movie>) {
        movieAdapter?.submitList(movies)
    }
}
