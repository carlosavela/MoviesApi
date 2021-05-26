package com.ing.cavl.moviesapi.ui.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ing.cavl.moviesapi.ui.data.model.MovieEntity

@Dao
interface MovieDao {
    @Query("Select * From movieentity")
    suspend fun getAllMovies() : List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)
}