package com.ing.cavl.moviesapi.ui.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ing.cavl.moviesapi.ui.aplication.AppConstants
import com.ing.cavl.moviesapi.ui.data.model.Movie
import com.ing.cavl.moviesapi.ui.data.model.MovieDetail
import com.ing.cavl.moviesapi.ui.data.model.MovieList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// este va a ser el encargado para traer los datos
interface WebService {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movie_id: String,
        @Query("api_key") apiKey: String
    ): MovieDetail

    @GET("movie/upcoming")
    suspend fun getUpcomingMoreMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MovieList

}

// hacer la peticion al servidor atravez de retrofit

object RetrofitClient {
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}