package com.ing.cavl.moviesapi.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.ing.cavl.moviesapi.ui.core.Resource
import com.ing.cavl.moviesapi.ui.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repo: MovieRepository) : ViewModel() {

    // metodos para comunicarse con el repository

    fun fetchUpcomingMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getUpcomingMovies()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
    fun fetchUpcomingMoreMovies(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getUpcomingMoreMovies(page)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchTopRatedMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getTopRatedMovies()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchPopularMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getPopularMovies()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    // Para que se retornen de manera secuencial

    fun fetchScreenMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(
                Resource.Success(
                    NTupla(
                        repo.getPopularMovies(),
                        repo.getTopRatedMovies(),
                        repo.getUpcomingMovies(),
                        repo.getNowPlayingMovies()
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchDetailMovie(id_movie: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getDeitalMovie(id_movie)))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}

class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}

// por si queremos retornar mas elementos

data class NTupla<T1, T2, T3, T4>(val t1: T1, val t2: T2, val t3: T3, val t4: T4)