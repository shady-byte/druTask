package com.example.drutask.domain.model

import com.example.drutask.data.model.dataModel.Movie
import com.example.drutask.utils.CategoriesTypeEnum

data class CategoryData(
    val category: CategoriesTypeEnum,
    var movies: List<Movie> = emptyList()
)