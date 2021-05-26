package com.ing.cavl.moviesapi.ui.repository

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
        dataSourceRemote.getUpcomingMovies().results.forEach {
            dataSourceLocal.saveMovie(it.toMovieEntity("upcoming"))
        }
        return dataSourceLocal.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): MovieList {
        dataSourceRemote.getTopRaterMovies().results.forEach {
            dataSourceLocal.saveMovie(it.toMovieEntity("toprater"))
        }
        return dataSourceLocal.getTopRaterMovies()
    }

    override suspend fun getPopularMovies(): MovieList {
        dataSourceRemote.getPopularMovies().results.forEach {
            dataSourceLocal.saveMovie(it.toMovieEntity("popular"))
        }
        return dataSourceLocal.getPopularMovies()
    }

    override suspend fun getNowPlayingMovies(): MovieList {
        dataSourceRemote.getNowPlayingMovies().results.forEach {
            dataSourceLocal.saveMovie(it.toMovieEntity("nowplaying"))
        }
        return dataSourceLocal.getNowPlayingMovies()
    }
}

