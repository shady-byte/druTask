package com.example.drutask.data.model.reponse

import com.example.drutask.data.model.dataModel.Movie

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>
)