package com.ing.cavl.moviesapi.ui.data.local

import com.ing.cavl.moviesapi.ui.data.model.MovieEntity
import com.ing.cavl.moviesapi.ui.data.model.MovieList
import com.ing.cavl.moviesapi.ui.data.model.toMovieList

class LocalMovieDataSource(private val dao: MovieDao) {

    suspend fun getUpcomingMovies(): MovieList {
        return dao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }

    suspend fun getTopRaterMovies(): MovieList{
        return dao.getAllMovies().filter { it.movie_type == "toprater"  }.toMovieList()

    }

    suspend fun getPopularMovies(): MovieList {
        return dao.getAllMovies().filter { it.movie_type == "popular"  }.toMovieList()

    }

    suspend fun getNowPlayingMovies(): MovieList {
        return dao.getAllMovies().filter { it.movie_type == "nowplaying"  }.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity){
        dao.saveMovie(movie)
    }

//    suspend fun getUpcomingMoreMovies(page: Int): MovieList =
//        webService.getUpcomingMoreMovies(AppConstants.API_KEY, page)
}