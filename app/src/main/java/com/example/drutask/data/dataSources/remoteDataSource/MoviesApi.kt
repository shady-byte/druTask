package com.example.drutask.data.dataSources.remoteDataSource

import com.example.drutask.data.model.reponse.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("{category}?api_key=${ApiUrls.API_KEY}&language=${ApiUrls.LANGUAGE}")
    suspend fun getMovies(
        @Path("category") category: String,
        @Query("page") pageNumber: Int
    ): MoviesResponse
}