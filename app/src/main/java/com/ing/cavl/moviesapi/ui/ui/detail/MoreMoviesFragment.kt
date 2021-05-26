package com.ing.cavl.moviesapi.ui.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ing.cavl.moviesapi.R
import com.ing.cavl.moviesapi.databinding.FragmentMoreMoviesBinding
import com.ing.cavl.moviesapi.ui.core.Resource
import com.ing.cavl.moviesapi.ui.data.model.Movie
import com.ing.cavl.moviesapi.ui.data.remote.RemoteMovieDataSource
import com.ing.cavl.moviesapi.ui.presentation.MovieViewModel
import com.ing.cavl.moviesapi.ui.presentation.MovieViewModelFactory
import com.ing.cavl.moviesapi.ui.repository.MovieRepositoryImpl
import com.ing.cavl.moviesapi.ui.repository.RetrofitClient
import com.ing.cavl.moviesapi.ui.ui.movie.adapters.concat.MovieAdapter

class MoreMoviesFragment : Fragment(R.layout.fragment_more_movies),
    MovieAdapter.onMovieClickListener {

    private var binding: FragmentMoreMoviesBinding? = null
    private lateinit var moviesList: MutableList<Movie>
    private lateinit var movieAdapter: MovieAdapter
    lateinit var layoutManager: GridLayoutManager
    var noLoading = true

    //lateinit var layoutManager1: LinearLayoutManager
    var page = 1
    val totalPages = 500

    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webservice)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoreMoviesBinding.bind(view)
        moviesList = mutableListOf()
        movieAdapter = MovieAdapter(moviesList, this@MoreMoviesFragment)
        layoutManager = GridLayoutManager(requireContext(), 3)
        binding!!.rvMoreMovies.layoutManager = layoutManager
        binding!!.rvMoreMovies.adapter = movieAdapter
        getData(page)
        addScrollingListener()
    }

    private fun addScrollingListener() {
        binding!!.rvMoreMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0) {
                    val vItem = layoutManager.childCount
                    val lItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val count = movieAdapter.itemCount

                    if (noLoading) {
                        if (vItem + lItem >= count && page <= totalPages) {
                            page++
                            getMoreData(page)
                            movieAdapter.notifyDataSetChanged()
                        } else {
                            Toast.makeText(requireContext(), "Entro en el else", Toast.LENGTH_SHORT)
                                .show()
                            page = 1
                        }
                    }
                }

//                if (layoutManager.findLastCompletelyVisibleItemPosition() == moviesList.size - 1 && page <= totalPages) {
//                    page++
//                    getMoreData(page)
//                    //movieAdapter.notifyDataSetChanged()
//                } else {
//                    Toast.makeText(requireContext(), "Fin", Toast.LENGTH_SHORT).show()
//                }


            }


        })
    }

    private fun getData(page: Int) {
        viewModel.fetchUpcomingMoreMovies(page).observe(viewLifecycleOwner, { result ->

            when (result) {

                is Resource.Loading -> {
                    Log.d("More", "Loading...")
                    binding!!.progres.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding!!.progres.visibility = View.GONE
                    Log.d("More", "Success...")
                    moviesList = result.data.results as MutableList<Movie>
                    movieAdapter = MovieAdapter(moviesList, this@MoreMoviesFragment)
                    binding!!.rvMoreMovies.adapter = movieAdapter
                    //movieAdapter.notifyDataSetChanged()
                }

                is Resource.Failure -> {
                    binding!!.progres.visibility = View.GONE

                    Log.d("More", "Error...")

                    Toast.makeText(
                        requireContext(),
                        "Error ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })
    }

    fun getMoreData(page: Int) {
        viewModel.fetchUpcomingMoreMovies(page).observe(viewLifecycleOwner, { result ->

            when (result) {

                is Resource.Loading -> {
                    noLoading = false
                    Log.d("More", "Loading...")
                    binding!!.progres.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    noLoading = true
                    binding!!.progres.visibility = View.GONE
                    Log.d("More", "Success...")
                    moviesList.addAll(result.data.results)
                    movieAdapter = MovieAdapter(moviesList, this@MoreMoviesFragment)
                    binding!!.rvMoreMovies.adapter = movieAdapter
                    movieAdapter.notifyDataSetChanged()
                }

                is Resource.Failure -> {
                    binding!!.progres.visibility = View.GONE

                    Log.d("More", "Error...")

                    Toast.makeText(
                        requireContext(),
                        "Error ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
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
        try {
            val action =
                MoreMoviesFragmentDirections.actionMoreMoviesFragmentToMovieDetailFragment(
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
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "$e", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onTitleClick(movie: Movie) {
        Toast.makeText(requireContext(), "${movie.title} , ${movie.genre_ids}", Toast.LENGTH_SHORT)
            .show()
    }
}