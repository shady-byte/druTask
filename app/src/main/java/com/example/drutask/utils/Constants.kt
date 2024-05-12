package com.example.drutask.utils

import com.example.drutask.data.dataSources.remoteDataSource.ApiUrls
import com.example.drutask.domain.model.CategoryData

object Constants {

    fun createImageUrl(splitUrl: String): String {
        return ApiUrls.MOVIE_IMAGE_BASE_URL + splitUrl
    }

    val categoriesList = listOf(
        CategoryData(
            category = CategoriesTypeEnum.POPULAR
        ),
        CategoryData(
            category = CategoriesTypeEnum.TOP_RATED
        ),
        CategoryData(
            category = CategoriesTypeEnum.NOW_PLAYING
        ),
        CategoryData(
            category = CategoriesTypeEnum.UPCOMING
        ),
    )
}