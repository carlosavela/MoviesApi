package com.ing.cavl.moviesapi.ui.repository

import com.ing.cavl.moviesapi.ui.core.InternetCheck
import com.ing.cavl.moviesapi.ui.data.local.LocalMovieDataSource
import com.ing.cavl.moviesapi.ui.data.model.MovieDetail
import com.ing.cavl.moviesapi.ui.data.model.MovieList
import com.ing.cavl.moviesapi.ui.data.model.toMovieEntity
import com.ing.cavl.moviesapi.ui.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    override suspend fun getDeitalMovie(id_movie: String): MovieDetail =
        dataSourceRemote.getMovieDetail(id_movie)

    override suspend fun getUpcomingMoreMovies(page: Int): MovieList =
        dataSourceRemote.getUpcomingMoreMovies(page)

    override suspend fun getUpcomingMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getUpcomingMovies().results.forEach {
                dataSourceLocal.saveMovie(it.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }

    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getTopRaterMovies().results.forEach {
                dataSourceLocal.saveMovie(it.toMovieEntity("toprater"))
            }
            dataSourceLocal.getTopRaterMovies()
        } else {
            dataSourceLocal.getTopRaterMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getPopularMovies().results.forEach {
                dataSourceLocal.saveMovie(it.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        } else {
            dataSourceLocal.getPopularMovies()
        }
    }

    override suspend fun getNowPlayingMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getNowPlayingMovies().results.forEach {
                dataSourceLocal.saveMovie(it.toMovieEntity("nowplaying"))
            }
            dataSourceLocal.getNowPlayingMovies()
        } else {
            dataSourceLocal.getNowPlayingMovies()
        }
    }
}

