package com.ing.cavl.moviesapi.ui.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ing.cavl.moviesapi.ui.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao


    // -> Singleton Room
    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "movie_table"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstanace(){
            INSTANCE = null
        }
    }
}