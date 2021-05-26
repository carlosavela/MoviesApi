package com.ing.cavl.moviesapi.ui.repository

import com.ing.cavl.moviesapi.ui.data.model.Movie
import com.ing.cavl.moviesapi.ui.data.model.MovieDetail
import com.ing.cavl.moviesapi.ui.data.model.MovieList
import com.ing.cavl.moviesapi.ui.data.remote.MovieDataSource

class MovieRepositoryImpl(private val dataSource: MovieDataSource) : MovieRepository {

    override suspend fun getDeitalMovie(id_movie: String): MovieDetail =
        dataSource.getMovieDetail(id_movie)

    override suspend fun getUpcomingMoreMovies(page: Int): MovieList =
        dataSource.getUpcomingMoreMovies(page)

    override suspend fun getUpcomingMovies(): MovieList = dataSource.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRaterMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()

    override suspend fun getNowPlayingMovies(): MovieList = dataSource.getNowPlayingMovies()


}