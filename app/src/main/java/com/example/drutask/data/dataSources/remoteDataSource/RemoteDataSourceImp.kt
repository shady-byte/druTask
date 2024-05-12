package com.example.drutask.data.dataSources.remoteDataSource

import com.example.drutask.data.dataSources.RemoteDataSource
import com.example.drutask.data.model.dataModel.Movie
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val moviesApi: MoviesApi,
): RemoteDataSource {
    override suspend fun getMovies(pageNumber: Int, category: String): List<Movie> {
        val response = moviesApi.getMovies(category, pageNumber)
        return response.results
    }
}