package com.example.nobelabookreader.APIproducts

import com.example.nobelabookreader.models.NobelaResponse
import com.example.nobelabookreader.models.NobelaResponseItem
import com.example.nobelabookreader.util.Constants.Companion.API_KEY
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsAPI {
    @GET("/products")
    suspend fun getHeadlines(
        @Query("tittle")
        tittle: String,
        @Query("description")
        description: String,
        @Query("image")
        image: String,
        @Query("rating")
        rating: Int,
        @Query("genre")
        genre: String,
        @Query("body")
        body: String,
        @Query("apiKEY")
        apiKey: String = API_KEY
    ):Response
}