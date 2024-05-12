package com.example.drutask.data.dataSources

import com.example.drutask.data.model.dataModel.Movie

interface LocalDataSource {
    suspend fun addMovies(movies: List<Movie>, category: String): List<Long>
    suspend fun getMoviesByCategory(pageNumber: Int, category: String): List<Movie>
    suspend fun deleteMovies()
}