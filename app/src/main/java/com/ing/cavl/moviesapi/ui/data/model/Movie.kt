package com.ing.cavl.moviesapi.ui.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Movie(
    val id: Int = -1,
    val adult: Boolean = false,
    val genre_ids: List<Int> = listOf(),
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
    val vote_average: Double = 0.0
)

