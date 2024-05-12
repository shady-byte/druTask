package com.example.drutask.domain.repository

import com.example.drutask.data.model.dataModel.Movie
import com.example.drutask.utils.ResponseStatus

interface MoviesRepository {
    suspend fun addMovies(movies: List<Movie>, category: String): ResponseStatus<Boolean>
    suspend fun getMovies(pageNumber: Int, category: String): ResponseStatus<List<Movie>>
    suspend fun deleteMovies(): ResponseStatus<Boolean>
}