package com.example.drutask.domain.usecase

import com.example.drutask.data.model.dataModel.Movie
import com.example.drutask.domain.model.MovieDataWrapper
import com.example.drutask.domain.repository.MoviesRepository
import com.example.drutask.utils.ResponseStatus
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
): BaseUseCase<MovieDataWrapper, List<Movie>>() {

    override suspend fun execute(parameters: MovieDataWrapper): ResponseStatus<List<Movie>> {
        return moviesRepository.getMovies(parameters.pageNumber, parameters.categoryType)
    }
}