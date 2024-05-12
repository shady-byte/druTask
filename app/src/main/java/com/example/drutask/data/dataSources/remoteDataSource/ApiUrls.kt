package com.example.drutask.data.dataSources.remoteDataSource

import com.example.drutask.BuildConfig


object ApiUrls {
    const val MOVIES_BASE_URL = "https://api.themoviedb.org/3/movie/"
    const val MOVIE_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    const val API_KEY = BuildConfig.API_KEY
    const val LANGUAGE = "en-US"
}