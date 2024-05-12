package com.example.drutask.data.repository

import com.example.drutask.data.dataSources.LocalDataSource
import com.example.drutask.data.dataSources.RemoteDataSource
import com.example.drutask.data.model.dataModel.Movie
import com.example.drutask.domain.repository.MoviesRepository
import com.example.drutask.utils.ResponseStatus
import com.example.drutask.base.SharedPreferencesManager
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val sharedPrefManager: SharedPreferencesManager
) : MoviesRepository {
    override suspend fun addMovies(
        movies: List<Movie>,
        category: String
    ): ResponseStatus<Boolean> {
        return try {
            val response = localDataSource.addMovies(movies, category)
            if (response.isNotEmpty())
                ResponseStatus.OnSuccess(true)
            else
                ResponseStatus.OnError(Exception("failed to add movies in database"))
        } catch (ex: Exception) {
            ResponseStatus.OnError(ex)
        }
    }

    override suspend fun getMovies(
        pageNumber: Int,
        category: String
    ): ResponseStatus<List<Movie>> {
        try {
            if (checkForDataLastUpdate()) {
                val movies = localDataSource.getMoviesByCategory(pageNumber, category)
                if (movies.isNotEmpty() && movies.size > (pageNumber - 1) * 20) {
                    return ResponseStatus.OnSuccess(movies)
                } else {
                    val result = remoteDataSource.getMovies(pageNumber, category)
                    if (result.isNotEmpty()) {
                        addMovies(result, category)
                        val localMovies = localDataSource.getMoviesByCategory(pageNumber, category)
                        return ResponseStatus.OnSuccess(localMovies)
                    }
                    return ResponseStatus.OnError(Exception("failed to fetch remote data"))
                }
            } else {
                deleteMovies()
                sharedPrefManager.setLastUpdateTime()
                val result = remoteDataSource.getMovies(1, category)
                if (result.isNotEmpty()) {
                    addMovies(result, category)
                    return ResponseStatus.OnSuccess(result)
                }
                return ResponseStatus.OnError(Exception("failed to fetch remote data"))
            }
        } catch (ex: Exception) {
            return ResponseStatus.OnError(ex)
        }
    }

    override suspend fun deleteMovies(): ResponseStatus<Boolean> {
        return try {
            localDataSource.deleteMovies()
            ResponseStatus.OnSuccess(true)
        } catch (ex: Exception) {
            ResponseStatus.OnError(ex)
        }
    }

    private fun checkForDataLastUpdate(): Boolean {
        val hoursInMillis = 4 * 60 * 60 * 1000
        return (System.currentTimeMillis() - sharedPrefManager.getLastUpdateTime()) < hoursInMillis
    }
}