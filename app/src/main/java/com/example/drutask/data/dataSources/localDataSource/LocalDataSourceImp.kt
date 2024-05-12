package com.example.drutask.data.dataSources.localDataSource

import com.example.drutask.data.dataSources.LocalDataSource
import com.example.drutask.data.model.dataModel.Movie
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val databaseDao: DatabaseDao
) : LocalDataSource {
    override suspend fun addMovies(movies: List<Movie>, category: String): List<Long> {
        val localMovies = movies.map { movie ->
            movie.copy(category = category)
        }

        return databaseDao.addMovies(*localMovies.toTypedArray())
    }

    override suspend fun getMoviesByCategory(pageNumber: Int, category: String): List<Movie> {
        return databaseDao.getMoviesByCategory(category)
    }

    override suspend fun deleteMovies() {
        return databaseDao.deleteMovies()
    }
}