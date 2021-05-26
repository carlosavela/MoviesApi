package com.ing.cavl.moviesapi.ui.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Movie(
    val id: Int = -1,
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val original_title: String = "",
    val original_language: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val overview: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_count: Int = 0,
    val vote_average: Double = 0.0,
    val movie_type: String = ""
)

data class MovieList(val results: List<Movie> = listOf())

data class MovieDetail(
    val id: Int = -1,
    val adult: Boolean = false,
    val genres: List<Genres> = listOf(),
    val backdrop_path: String = "",
    val original_title: String = "",
    val original_language: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val overview: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_count: Int = 0,
    val vote_average: Double = 0.0
)

data class Genres(
    val id: Int,
    val name: String
)


// --> Room
@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String = "",
    @ColumnInfo(name = "original_title")
    val original_title: String = "",
    @ColumnInfo(name = "original_language")
    val original_language: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: Double = 0.0,
    @ColumnInfo(name = "poster_path")
    val poster_path: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "release_date")
    val release_date: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int = 0,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double = 0.0,
    @ColumnInfo(name = "colum_info")
    val movie_type: String = ""
)

fun List<MovieEntity>.toMovieList(): MovieList {

    val resultList = mutableListOf<Movie>()
    this.forEach { movieEntity ->
        resultList.add(movieEntity.toMovie())

    }
    return MovieList(resultList)
}

fun MovieEntity.toMovie(): Movie = Movie(
    this.id,
    this.adult,
    this.backdrop_path,
    this.original_title,
    this.original_language,
    this.popularity,
    this.poster_path,
    this.overview,
    this.release_date,
    this.title,
    this.video,
    this.vote_count,
    this.vote_average,
    this.movie_type
)

