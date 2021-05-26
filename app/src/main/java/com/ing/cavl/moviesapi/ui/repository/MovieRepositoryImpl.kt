package com.ing.cavl.moviesapi.ui.repository

import com.ing.cavl.moviesapi.ui.data.model.MovieDetail
import com.ing.cavl.moviesapi.ui.data.model.MovieList
import com.ing.cavl.moviesapi.ui.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(private val dataSourceRemote: RemoteMovieDataSource) : MovieRepository {

    override suspend fun getDeitalMovie(id_movie: String): MovieDetail =
        dataSourceRemote.getMovieDetail(id_movie)

    override suspend fun getUpcomingMoreMovies(page: Int): MovieList =
        dataSourceRemote.getUpcomingMoreMovies(page)

    override suspend fun getUpcomingMovies(): MovieList = dataSourceRemote.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSourceRemote.getTopRaterMovies()

    override suspend fun getPopularMovies(): MovieList = dataSourceRemote.getPopularMovies()

    override suspend fun getNowPlayingMovies(): MovieList = dataSourceRemote.getNowPlayingMovies()


}