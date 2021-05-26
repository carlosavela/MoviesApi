package com.ing.cavl.moviesapi.ui.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.ing.cavl.moviesapi.R
import com.ing.cavl.moviesapi.databinding.FragmentMoviesBinding
import com.ing.cavl.moviesapi.ui.core.Resource
import com.ing.cavl.moviesapi.ui.data.local.AppDataBase
import com.ing.cavl.moviesapi.ui.data.local.LocalMovieDataSource
import com.ing.cavl.moviesapi.ui.data.model.Movie
import com.ing.cavl.moviesapi.ui.data.remote.RemoteMovieDataSource
import com.ing.cavl.moviesapi.ui.presentation.MovieViewModel
import com.ing.cavl.moviesapi.ui.presentation.MovieViewModelFactory
import com.ing.cavl.moviesapi.ui.repository.MovieRepositoryImpl
import com.ing.cavl.moviesapi.ui.repository.RetrofitClient
import com.ing.cavl.moviesapi.ui.ui.movie.adapters.concat.*

class MoviesFragment : Fragment(R.layout.fragment_movies), MovieAdapter.onMovieClickListener, UpcomingConcatAdapter.onSeeMoreMovies {

    private var binding: FragmentMoviesBinding? = null
    private lateinit var concatAdapter: ConcatAdapter


    // by delegador para crear la instancia de viewModel
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webservice),
                LocalMovieDataSource(AppDataBase.getDataBase(requireContext()).movieDao())
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)
        concatAdapter = ConcatAdapter()

        viewModel.fetchScreenMovies().observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading....")
                    binding!!.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding!!.progressBar.visibility = View.GONE
                    Log.d(
                        "LiveData",
                        "${result.data.t1}  \n \n ${result.data.t2} \n \n ${result.data.t3} \n \n ${result.data.t4}"
                    )
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            NowConcatAdapter(
                                MovieAdapter(
                                    result.data.t4.results,
                                    this@MoviesFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.t3.results,
                                    this@MoviesFragment
                                ),this@MoviesFragment
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.t1.results,
                                    this@MoviesFragment
                                )
                            )
                        )
                        addAdapter(
                            3,
                            TopRetedConcatAdapter(
                                MovieAdapter(
                                    result.data.t2.results,
                                    this@MoviesFragment
                                )
                            )
                        )
                    }
                    binding!!.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding!!.progressBar.visibility = View.GONE
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onMovieClick(movie: Movie) {
        Log.d("test", "onMovieClick: $movie")
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.release_date,
            movie.original_language
        )
        findNavController().navigate(action)
    }

    override fun onTitleClick(movie: Movie) {
        viewModel.fetchDetailMovie("${movie.id}").observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("Title", "LoadingTitle....")
                }
                is Resource.Success -> {
                    Log.d(
                        "Title",
                        "${result.data.genres}"
                    )
                    val genres = result.data.genres
                    for ((id, gen) in genres) {
                        Log.d("Title", "$id $gen")
                    }
                }
                is Resource.Failure -> {
                    Log.d("Title", "Error: ")
                }
            }
        })
    }

    override fun onClickMoreMovis() {
        findNavController().navigate(R.id.action_moviesFragment_to_moreMoviesFragment)
    }
}
