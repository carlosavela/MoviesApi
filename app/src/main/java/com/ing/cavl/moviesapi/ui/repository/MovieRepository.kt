package com.ing.cavl.moviesapi.ui.repository

import com.ing.cavl.moviesapi.ui.data.model.MovieDetail
import com.ing.cavl.moviesapi.ui.data.model.MovieList

interface MovieRepository {
    // -> suspend es para suspendera la funcion hasta que el servidor nos regrese la informacion

    suspend fun getUpcomingMoreMovies(page: Int): MovieList

    suspend fun getUpcomingMovies(): MovieList

    suspend fun getTopRatedMovies(): MovieList

    suspend fun getPopularMovies(): MovieList

    suspend fun getNowPlayingMovies(): MovieList

    suspend fun getDeitalMovie(id_movie: String): MovieDetail

}