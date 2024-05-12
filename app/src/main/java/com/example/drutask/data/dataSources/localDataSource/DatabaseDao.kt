package com.example.drutask.data.dataSources.localDataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.drutask.data.model.dataModel.Movie

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(vararg movies: Movie): List<Long>

    @Query("SELECT * FROM movies_table WHERE category=:category")
    fun getMoviesByCategory(category: String): List<Movie>

    @Query("DELETE FROM movies_table")
    fun deleteMovies()
}