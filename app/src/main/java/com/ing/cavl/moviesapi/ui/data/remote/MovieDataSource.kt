package com.ing.cavl.moviesapi.ui.data.remote

import com.ing.cavl.moviesapi.ui.aplication.AppConstants
import com.ing.cavl.moviesapi.ui.data.model.Movie
import com.ing.cavl.moviesapi.ui.data.model.MovieDetail
import com.ing.cavl.moviesapi.ui.data.model.MovieList
import com.ing.cavl.moviesapi.ui.repository.WebService
import com.ing.cavl.moviesapi.ui.ui.detail.MovieDetailFragmentArgs

class MovieDataSource(private val webService: WebService) {


    // se crea la consulta para traer la informacion

    suspend fun getMovieDetail(id_movie: String): MovieDetail =
        webService.getDetailMovie(id_movie, AppConstants.API_KEY)

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRaterMovies(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)

    suspend fun getNowPlayingMovies(): MovieList =
        webService.getNowPlayingMovies(AppConstants.API_KEY)

    suspend fun getUpcomingMoreMovies(page: Int): MovieList =
        webService.getUpcomingMoreMovies(AppConstants.API_KEY, page)

}