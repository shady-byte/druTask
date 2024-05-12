package com.example.drutask.data.dataSources

import com.example.drutask.data.model.dataModel.Movie

interface RemoteDataSource {
    suspend fun getMovies(pageNumber: Int, category: String): List<Movie>
}